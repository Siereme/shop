package app.constructor.search.impl;

import app.constructor.search.IQueryConstructor;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.OptionValueDTO;
import app.model.dto.search.SearchCategoryDTO;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.function.Consumer;

public class CategoryQueryConstructor implements IQueryConstructor<SearchCategoryDTO> {

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> search(SearchPredicateFactory f, SearchCategoryDTO config) {
        return b -> {
            b.must(f.matchAll());
            b.must(f.nested().objectField("categories")
                    .nest(f.bool(o ->
                        o.must(f.wildcard().field("categories.path")
                                .matching(config.getCategory().getPath() + "*"))
                    )));
        };
    }

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> searchByOptions(SearchPredicateFactory f, SearchCategoryDTO config) {
        return b -> {
            b.must(f.matchAll());
            b.must(f.nested().objectField("categories")
                    .nest(f.bool(o ->
                            o.must(f.wildcard().field("categories.path")
                                    .matching(config.getCategory().getPath() + "*"))
                    )));
//            if(config.getRangePrice() != null){
//                b.must(f.range().field("price").between(config.getRangePrice().getPriceMin(), config.getRangePrice().getPriceMax()));
//            }
            if(config.getOptions() != null && !config.getOptions().isEmpty()){
                for (OptionDTO option : config.getOptions()) {
                    b.must(f.match().field("options.option.type").matching(option.getType()));
                    b.must(f.bool(v -> {
                        for (OptionValueDTO optionValue : option.getValues()) {
                            v.should(f.match().field("options.value").matching(optionValue.getValue()));
                        }
                    }));
                }
            }
        };
    }
}
