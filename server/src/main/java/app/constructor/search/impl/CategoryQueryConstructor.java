package app.constructor.search.impl;

import app.constructor.search.IQueryConstructor;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.OptionValueDTO;
import app.model.dto.search.SearchCategoryDTO;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.stereotype.Component;

@Component
public class CategoryQueryConstructor implements IQueryConstructor<SearchCategoryDTO> {

    @Override
    public SearchPredicate search(SearchPredicateFactory f, SearchCategoryDTO config) {
        return f.bool(b -> b.must(f.nested().objectField("categories")
                .nest(f.bool(o ->
                        o.must(f.wildcard().field("categories.path")
                                .matching(config.getCategory().getPath() + "*"))
                )))).toPredicate();
    }

    @Override
    public SearchPredicate searchByOptions(SearchPredicateFactory f, SearchCategoryDTO config) {
        return f.bool(b -> {
            b.must(f.nested().objectField("categories")
                    .nest(f.bool(o ->
                            o.must(f.wildcard().field("categories.path")
                                    .matching(config.getCategory().getPath() + "*"))
                    )));
            if (config.getRangePrice() != null && config.getRangePrice().isValid()) {
                b.must(f.range().field("price").between(config.getRangePrice().getPriceMin(), config.getRangePrice().getPriceMax()));
            }
            if (config.getOptions() != null && !config.getOptions().isEmpty()) {
                for (OptionDTO option : config.getOptions()) {
                    b.must(f.match().field("options.option.type").matching(option.getType()));
                    b.must(f.bool(v -> {
                        for (OptionValueDTO optionValue : option.getValues()) {
                            v.should(f.match().field("options.value").matching(optionValue.getValue()));
                        }
                    }));
                }
            }
        }).toPredicate();
    }
}
