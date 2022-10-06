package app.constructor.search;

import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public interface IQueryConstructor {
    Consumer<? super BooleanPredicateClausesStep<?>> search(SearchPredicateFactory f, String query);

    Consumer<? super BooleanPredicateClausesStep<?>> searchByOptions(SearchPredicateFactory f, String query, Map<String, Set<String>> options);
}
