package app.constructor.search.impl;

import app.constructor.search.IQueryConstructor;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.OptionValueDTO;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.List;
import java.util.function.Consumer;

public class SearchQueryConstructor implements IQueryConstructor {

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> search(SearchPredicateFactory f, String query) {
        return b -> {
            b.must(f.matchAll());
            b.must(f.match().field("title").matching(query)
                    .constantScore().boost(2.0f));
            b.should(f.match().field("description.shortDescription").matching(query));
            b.should(f.match().field("description.longDescription").matching(query));
        };
    }

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> searchByOptions(SearchPredicateFactory f, String query, List<OptionDTO> options) {
        return b -> {
            b.must(f.match().field("title").matching(query)
                    .constantScore().boost(6.0f));
            b.should(f.match().field("description.shortDescription").matching(query));
            b.should(f.match().field("description.longDescription").matching(query));
            if(!options.isEmpty()){
                b.must(f.nested().objectField("options")
                        .nest(f.bool(o -> {
                            o.must(f.matchAll());
                            for (OptionDTO option : options) {
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
