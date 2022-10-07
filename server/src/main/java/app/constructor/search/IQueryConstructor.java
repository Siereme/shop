package app.constructor.search;

import app.model.dto.search.OptionDTO;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.List;
import java.util.function.Consumer;

public interface IQueryConstructor {
    Consumer<? super BooleanPredicateClausesStep<?>> search(SearchPredicateFactory f, String query);

    Consumer<? super BooleanPredicateClausesStep<?>> searchByOptions(SearchPredicateFactory f, String query, List<OptionDTO> options);
}
