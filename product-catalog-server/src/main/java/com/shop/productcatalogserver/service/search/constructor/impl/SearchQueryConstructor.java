package com.shop.productcatalogserver.service.search.constructor.impl;

import com.shop.productcatalogserver.dto.search.OptionDTO;
import com.shop.productcatalogserver.dto.search.OptionValueDTO;
import com.shop.productcatalogserver.dto.search.SearchDTO;
import com.shop.productcatalogserver.service.search.constructor.IQueryConstructor;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchQueryConstructor implements IQueryConstructor<SearchDTO> {

    @Override
    public SearchPredicate search(SearchPredicateFactory f, SearchDTO config) {
        return f.bool(b -> {
            b.must(f.match().field("name").matching(config.getQuery())
                    .constantScore().boost(2.0f));
            b.should(f.match().field("description.shortDescription").matching(config.getQuery())
                    .constantScore().boost(1.0f));
            b.should(f.match().field("description.longDescription").matching(config.getQuery())
                    .constantScore().boost(1.0f));
        }).toPredicate();
    }

    @Override
    public SearchPredicate searchByOptions(SearchPredicateFactory f, SearchDTO config) {
        return f.bool(b -> {
            b.must(f.bool(q -> {
                q.must(f.match().field("name").matching(config.getQuery())
                        .constantScore().boost(2.0f));
                q.should(f.match().field("description.shortDescription").matching(config.getQuery())
                        .constantScore().boost(1.0f));
                q.should(f.match().field("description.longDescription").matching(config.getQuery())
                        .constantScore().boost(1.0f));
            }));
            if (config.getRangePrice() != null && config.getRangePrice().isValid()) {
                b.must(f.range().field("price")
                        .between(config.getRangePrice().getPriceMin(), config.getRangePrice().getPriceMax()));
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
