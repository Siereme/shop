package app.service.search.query;

import app.constructor.search.impl.SearchQueryConstructor;
import app.model.dto.search.SearchDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SearchQueryService extends AbstractSearchQueryService<SearchDTO> {

    public SearchQueryService(EntityManager entityManager, SearchQueryConstructor queryConstructor) {
        super(entityManager, queryConstructor);
    }

}
