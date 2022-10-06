package app.constructor.search.impl;

import app.constructor.search.IQueryConstructor;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class CategoryQueryConstructor implements IQueryConstructor {

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> search(SearchPredicateFactory f, String path) {
        return b -> {
            b.must(f.matchAll());
            b.should(f.nested().objectField("categories")
                    .nest(f.bool(o -> {
                        o.must(f.matchAll());
                        o.must(f.match().field("categories.path").matching(path));
                    })));
        };
    }

    @Override
    public Consumer<? super BooleanPredicateClausesStep<?>> searchByOptions(SearchPredicateFactory f, String path, Map<String, Set<String>> options) {
        return b -> {
            b.must(f.matchAll());
            b.should(f.nested().objectField("categories")
                    .nest(f.bool(o -> {
                        o.must(f.matchAll());
                        o.must(f.match().field("categories.path").matching(path));
                    })));
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
