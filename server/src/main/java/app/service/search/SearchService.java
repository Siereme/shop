package app.service.search;

import app.constructor.search.IQueryConstructor;
import app.constructor.search.impl.CategoryQueryConstructor;
import app.constructor.search.impl.SearchQueryConstructor;
import app.constructor.search.impl.SearchResponseConstructor;
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

    private final SearchSession searchSession;

    public SearchService(EntityManager entityManager) throws InterruptedException {
        searchSession = Search.session( entityManager.getEntityManagerFactory().createEntityManager() );
    }

    public SearchResponse search(String query, int page){
        IQueryConstructor queryConstructor = new SearchQueryConstructor();
        List<Product> products = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.search(f, query)))
                .fetchHits(page * 24,24);

        SearchResponseConstructor<SearchResponse> builder = new SearchResponseConstructor<>(SearchResponse::new);
        return builder.construct(products);
    }

    public List<Product> searchByOptions(SearchDTO config){
        IQueryConstructor queryConstructor = new SearchQueryConstructor();
        return searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.searchByOptions(f, config.getQuery(), config.getOptions())))
                .fetchHits(config.getPage() * 24,24);
    }

    public SearchCategoryResponse searchCategory(CategoryConfigDTO config){
        ICategoryDTO category = categoryService.findByIdWithoutSubcategories(config.getCategoryId());

        IQueryConstructor queryConstructor = new CategoryQueryConstructor();
        List<Product> products = searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.search(f, category.getPath())))
                .fetchHits(config.getPage() * config.getPageSize(), config.getPageSize());

        SearchResponseConstructor<SearchCategoryResponse> builder = new SearchResponseConstructor<>(SearchCategoryResponse::new);
        SearchCategoryResponse response = builder.construct(products);
        response.setCategory(category);

        if(config.isWithParent()){
            Category parentCategory = categoryService.getByPathAndDepth(category.getPath(), 1);
            response.setParentCategory(parentCategory);
        }

        return response;
    }

    public List<Product> searchCategoryByOptions(SearchCategoryDTO config){
        IQueryConstructor queryConstructor = new CategoryQueryConstructor();
        String path = config.getPath().substring(0, StringUtils.ordinalIndexOf(config.getPath(), "/", config.getDepth()) + 1);
        return searchSession.search( Product.class )
                .where(f -> f.bool(queryConstructor.searchByOptions(f, path, config.getOptions())))
                .fetchHits(config.getPage() * config.getPageSize(), config.getPageSize());
    }

}
