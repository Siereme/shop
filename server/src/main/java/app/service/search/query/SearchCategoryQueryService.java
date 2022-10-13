package app.service.search.query;

import app.constructor.search.IQueryConstructor;
import app.model.dto.search.SearchCategoryDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SearchCategoryQueryService extends AbstractSearchQueryService<SearchCategoryDTO> {

    public SearchCategoryQueryService(EntityManager entityManager, IQueryConstructor<SearchCategoryDTO> queryConstructor) {
        super(entityManager, queryConstructor);
    }

}
