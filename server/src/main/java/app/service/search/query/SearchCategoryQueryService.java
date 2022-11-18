package app.service.search.query;

import app.constructor.search.impl.CategoryQueryConstructor;
import app.model.dto.search.SearchCategoryDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SearchCategoryQueryService extends AbstractSearchQueryService<SearchCategoryDTO> {

    public SearchCategoryQueryService(EntityManager entityManager, CategoryQueryConstructor queryConstructor) {
        super(entityManager, queryConstructor);
    }

}
