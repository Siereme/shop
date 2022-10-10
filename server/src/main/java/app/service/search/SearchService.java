package app.service.search;

import app.constructor.search.impl.SearchQueryConstructor;
import app.constructor.search.impl.SearchResponseBuilder;
import app.model.dto.search.RangePriceDTO;
import app.model.dto.search.SearchDTO;
import app.model.dto.search.SearchResponse;
import app.model.product.Product;
import app.service.category.CategoryService;
import org.hibernate.search.engine.search.query.SearchResult;
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
        int shift = (config.getPage() - 1) * config.getPageSize();
        SearchQueryConstructor queryConstructor = new SearchQueryConstructor();
        SearchResult<Product> result = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.search(f, config)))
                .fetch(shift, config.getPageSize());

        List<Product> products = result.hits();
        long pagesCount = result.total().hitCount() / config.getPageSize();

        return responseBuilder
                .create(new SearchResponse())
                .setProducts(products)
                .setRangePrice(config.getRangePrice())
                .setOptions()
                .setPage(config.getPage(), pagesCount)
                .build();
    }

    @Override
    public SearchResponse searchByOptions(SearchDTO config){
        int shift = (config.getPage() - 1) * config.getPageSize();
        SearchQueryConstructor queryConstructor = new SearchQueryConstructor();
        SearchResult<Product> result = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.searchByOptions(f, config)))
                .fetch(shift, config.getPageSize());

        List<Product> products = result.hits();
        long pagesCount = result.total().hitCount() / config.getPageSize();

        RangePriceDTO rangePrice = config.getRangePrice();
        config.setRangePrice(null);

        List<Double> prices = searchSession.search(Product.class)
                .select(f -> f.field("price", Double.class))
                .where(f -> f.bool(queryConstructor.searchByOptions(f, config)))
                .fetchHits(shift, config.getPageSize());

        return responseBuilder
                .create(new SearchResponse())
                .setProducts(products)
                .setFullRangePrices(prices)
                .setRangePrice(rangePrice)
                .setCheckedOptions(config.getOptions())
                .setOptions()
                .setPage(config.getPage(), pagesCount)
                .build();
    }

}
