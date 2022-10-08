package app.service.search;

import app.constructor.search.impl.SearchQueryConstructor;
import app.constructor.search.impl.SearchResponseBuilder;
import app.model.dto.search.SearchDTO;
import app.model.dto.search.SearchResponse;
import app.model.product.Product;
import app.service.category.CategoryService;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchService implements ISearchService<SearchDTO> {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SearchResponseBuilder<SearchResponse> responseBuilder;

    private final SearchSession searchSession;

    public SearchService(EntityManager entityManager) {
        searchSession = Search.session( entityManager.getEntityManagerFactory().createEntityManager() );
    }

    @Override
    public SearchResponse search(SearchDTO config){
        SearchQueryConstructor queryConstructor = new SearchQueryConstructor();
        List<Product> products = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.search(f, config)))
                .fetchAllHits();
//                .fetchHits(page * 24,24);

        return responseBuilder
                .create(new SearchResponse())
                .setProducts(products)
                .setPriceRange(config.getPriceRange())
                .setOptions()
                .build();
    }

    @Override
    public SearchResponse searchByOptions(SearchDTO config){
        SearchQueryConstructor queryConstructor = new SearchQueryConstructor();
        List<Product> products = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.searchByOptions(f, config)))
                .fetchAllHits();
//                .fetchHits(config.getPage() * 24,24);

        return responseBuilder
                .create(new SearchResponse())
                .setProducts(products)
                .setPriceRange(config.getPriceRange())
                .setCheckedOptions(config.getOptions())
                .setOptions()
                .build();
    }

}
