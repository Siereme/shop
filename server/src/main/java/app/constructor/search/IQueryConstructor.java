package app.constructor.search;

import app.model.dto.search.ISearchDTO;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

public interface IQueryConstructor<T extends ISearchDTO> {

    SearchPredicate search(SearchPredicateFactory f, T config);

    SearchPredicate searchByOptions(SearchPredicateFactory f, T config);

}
