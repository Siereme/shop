package app.constructor.search.impl;

import app.constructor.search.IQueryConstructor;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class SearchQueryConstructor implements IQueryConstructor {

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> search(SearchPredicateFactory f, String query) {
        return b -> {
            b.must(f.matchAll());
            b.must(f.match().field("name").matching(query)
                    .constantScore().boost(2.0f));
            b.should(f.match().field("description.shortDescription").matching(query));
            b.should(f.match().field("description.longDescription").matching(query));
        };
    }

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> searchByOptions(SearchPredicateFactory f, String query, Map<String, Set<String>> options) {
        return b -> {
            b.should(f.match().field("name").matching(query)
                    .constantScore().boost(2.0f));
            b.should(f.match().field("description.shortDescription").matching(query));
            b.should(f.match().field("description.longDescription").matching(query));
            b.should(f.nested().objectField("options")
                    .nest(f.bool(o -> {
                        o.must(f.matchAll());
                        for (String name : options.keySet()) {
                            o.must(f.match().field("options.name").matching(name));
                            for (String value : options.get(name)) {
                                o.must(f.match().field("options.value").matching(value));
                            }
                        }
                    })));
        };
    }

}
