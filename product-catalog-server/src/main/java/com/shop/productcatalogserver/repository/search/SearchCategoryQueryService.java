package com.shop.productcatalogserver.repository.search;

import com.shop.productcatalogserver.dto.search.SearchCategoryDTO;
import com.shop.productcatalogserver.service.search.constructor.impl.CategoryQueryConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SearchCategoryQueryService extends AbstractSearchQueryService<SearchCategoryDTO> {

    public SearchCategoryQueryService(EntityManager entityManager, CategoryQueryConstructor queryConstructor) {
        super(entityManager, queryConstructor);
    }

}
