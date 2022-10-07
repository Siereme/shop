package app.service.search;

import app.constructor.search.impl.CategoryQueryConstructor;
import app.constructor.search.impl.SearchQueryConstructor;
import app.constructor.search.impl.SearchResponseBuilder;
import app.model.category.Category;
import app.model.dto.category.CategoryConfigDTO;
import app.model.dto.category.ICategoryDTO;
import app.model.dto.search.SearchCategoryDTO;
import app.model.dto.search.SearchCategoryResponse;
import app.model.dto.search.SearchDTO;
import app.model.dto.search.SearchResponse;
import app.model.product.Product;
import app.service.category.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SearchResponseBuilder responseBuilder;

    private final SearchSession searchSession;

    public SearchService(EntityManager entityManager) {
        searchSession = Search.session( entityManager.getEntityManagerFactory().createEntityManager() );
    }

    public SearchResponse search(String query, int page){
        SearchQueryConstructor queryConstructor = new SearchQueryConstructor();
        List<Product> products = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.search(f, query)))
                .fetchAllHits();
//                .fetchHits(page * 24,24);

        return (SearchResponse) responseBuilder
                .create(new SearchResponse())
                .setProducts(products)
                .setPriceRange()
                .setOptions()
                .build();
    }

    public SearchResponse searchByOptions(SearchDTO config){
        SearchQueryConstructor queryConstructor = new SearchQueryConstructor();
        List<Product> products = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.searchByOptions(f, config.getQuery(), config.getOptions())))
                .fetchAllHits();
//                .fetchHits(config.getPage() * 24,24);

        return (SearchResponse) responseBuilder
                .create(new SearchResponse())
                .setProducts(products)
                .setPriceRange()
                .setCheckedOptions(config.getOptions())
                .setOptions()
                .build();
    }

    public SearchCategoryResponse searchCategory(CategoryConfigDTO config){
        ICategoryDTO category = categoryService.findByIdWithoutSubcategories(config.getCategoryId());

        CategoryQueryConstructor queryConstructor = new CategoryQueryConstructor();
        List<Product> products = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.search(f, category.getPath())))
                .fetchAllHits();
//                .fetchHits(config.getPage() * config.getPageSize(), config.getPageSize());

        SearchCategoryResponse response = (SearchCategoryResponse) responseBuilder
                .create(new SearchCategoryResponse())
                .setProducts(products)
                .setPriceRange()
                .setOptions()
                .build();

        response.setCategory(category);

        if(config.isWithParent()){
            Category parentCategory = categoryService.getByPathAndDepth(category.getPath(), 1);
            response.setParentCategory(parentCategory);
        }

        return response;
    }

    public SearchResponse searchCategoryByOptions(SearchCategoryDTO config){
        CategoryQueryConstructor queryConstructor = new CategoryQueryConstructor();
        String path = config.getPath().substring(0, StringUtils.ordinalIndexOf(config.getPath(), "/", config.getDepth()) + 1);
        List<Product> products = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.searchByOptions(f, path, config.getOptions())))
                .fetchAllHits();
//                .fetchHits(config.getPage() * config.getPageSize(), config.getPageSize());
        return (SearchResponse) responseBuilder
                .create(new SearchResponse())
                .setProducts(products)
                .setPriceRange()
                .setCheckedOptions(config.getOptions())
                .setOptions()
                .build();
    }

}
