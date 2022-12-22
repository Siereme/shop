package com.shop.productcatalogserver.service.search.constructor;

import com.shop.productcatalogserver.dto.search.ISearchDTO;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

public interface IQueryConstructor<T extends ISearchDTO> {

    SearchPredicate search(SearchPredicateFactory f, T config);

    SearchPredicate searchByOptions(SearchPredicateFactory f, T config);

}
