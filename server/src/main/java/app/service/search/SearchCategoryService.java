package app.service.search;

import app.constructor.search.impl.CategoryQueryConstructor;
import app.constructor.search.impl.SearchResponseBuilder;
import app.model.category.Category;
import app.model.dto.category.ICategoryDTO;
import app.model.dto.search.RangePriceDTO;
import app.model.dto.search.SearchCategoryDTO;
import app.model.dto.search.SearchCategoryResponse;
import app.model.product.Product;
import app.service.category.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchCategoryService implements ISearchService<SearchCategoryDTO> {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SearchResponseBuilder<SearchCategoryResponse> responseBuilder;

    private final SearchSession searchSession;

    public SearchCategoryService(EntityManager entityManager) {
        searchSession = Search.session( entityManager.getEntityManagerFactory().createEntityManager() );
    }

    @Override
    public SearchCategoryResponse search(SearchCategoryDTO config){
        ICategoryDTO category = categoryService.findByIdWithoutSubcategories(config.getCategory().getId());
        config.getCategory().setPath(category.getPath());

        CategoryQueryConstructor queryConstructor = new CategoryQueryConstructor();
        int shift = (config.getPage() - 1) * config.getPageSize();
        SearchResult<Product> result = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.search(f, config)))
                .fetch(shift, config.getPageSize());

        List<Product> products = result.hits();
        long pagesCount = result.total().hitCount() / config.getPageSize();

        SearchCategoryResponse response = responseBuilder
                .create(new SearchCategoryResponse())
                .setProducts(products)
                .setRangePrice(config.getRangePrice())
                .setOptions()
                .setPage(config.getPage(), pagesCount)
                .build();

        response.setCategory(category);

        if(config.getCategory().isWithParent()){
            Category parentCategory = categoryService.getByPathAndDepth(category.getPath(), 1);
            response.setParentCategory(parentCategory);
        }

        return response;
    }

    @Override
    public SearchCategoryResponse searchByOptions(SearchCategoryDTO config){
        CategoryQueryConstructor queryConstructor = new CategoryQueryConstructor();
        String path = config.getCategory().getPath()
                .substring(0, StringUtils.ordinalIndexOf(config.getCategory().getPath(), "/", config.getCategory().getDepth()) + 1);
        config.getCategory().setPath(path);

        int shift = (config.getPage() - 1) * config.getPageSize();
        SearchResult<Product> result = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.searchByOptions(f, config)))
                .fetch(shift, config.getPageSize());

        List<Product> products = result.hits();
        long pagesCount = result.total().hitCount() / config.getPageSize();

        RangePriceDTO rangePrice = config.getRangePrice();
        config.setRangePrice(null);

        List<Double> prices = searchSession.search(Product.class)
                .select(f -> f.field("price", Double.class))
                .where(f -> f.bool(queryConstructor.search(f, config)))
                .fetchHits(shift, config.getPageSize());

        return responseBuilder
                .create(new SearchCategoryResponse())
                .setProducts(products)
                .setFullRangePrices(prices)
                .setRangePrice(rangePrice)
                .setCheckedOptions(config.getOptions())
                .setOptions()
                .setPage(config.getPage(), pagesCount)
                .build();
    }

}
