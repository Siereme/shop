package app.constructor.search.impl;

import app.constructor.search.IQueryConstructor;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.OptionValueDTO;
import app.model.dto.search.SearchCategoryDTO;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.List;
import java.util.function.Consumer;

public class CategoryQueryConstructor implements IQueryConstructor<SearchCategoryDTO> {

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> search(SearchPredicateFactory f, SearchCategoryDTO config) {
        return b -> {
            b.must(f.matchAll());
            b.should(f.nested().objectField("categories")
                    .nest(f.bool(o -> {
                        o.must(f.matchAll());
                        o.must(f.match().field("categories.path").matching(config.getCategory().getPath()));
                    })));
        };
    }

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> searchByOptions(SearchPredicateFactory f, SearchCategoryDTO config) {
        return b -> {
            b.must(f.matchAll());
            b.should(f.nested().objectField("categories")
                    .nest(f.bool(o -> {
                        o.must(f.matchAll());
                        o.must(f.match().field("categories.path").matching(config.getCategory().getPath()));
                    })));
            if(config.getPriceRange() != null){
                b.must(f.range().field("price").between(config.getPriceRange().getMin(), config.getPriceRange().getMax()));
            }
            if(!config.getOptions().isEmpty()){
                b.must(f.nested().objectField("options")
                        .nest(f.bool(o -> {
                            o.must(f.matchAll());
                            for (OptionDTO option : config.getOptions()) {
                                o.must(f.nested().objectField("options.optionType")
                                        .nest(f.bool().must(f.match().field("options.optionType.type").matching(option.getType()))));
                                for (OptionValueDTO optionValue : option.getValues()) {
                                    o.should(f.match().field("options.value").matching(optionValue.getValue()));
                                }
                            }
                        })));
            }
        };
    }
}
