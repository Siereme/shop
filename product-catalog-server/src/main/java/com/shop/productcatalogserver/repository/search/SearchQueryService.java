package com.shop.productcatalogserver.repository.search;

import com.shop.productcatalogserver.dto.search.SearchDTO;
import com.shop.productcatalogserver.service.search.constructor.impl.SearchQueryConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SearchQueryService extends AbstractSearchQueryService<SearchDTO> {

    public SearchQueryService(EntityManager entityManager, SearchQueryConstructor queryConstructor) {
        super(entityManager, queryConstructor);
    }

}
