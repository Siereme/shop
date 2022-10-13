package app.service.search.query;

import app.constructor.search.IQueryConstructor;
import app.model.dto.search.ISearchDTO;
import app.model.product.Product;
import app.model.product.option.OptionValue;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractSearchQueryService<T extends ISearchDTO> {

    private final IQueryConstructor<T> queryConstructor;
    private final SearchSession searchSession;

    public AbstractSearchQueryService(EntityManager entityManager, IQueryConstructor<T> queryConstructor) {
        searchSession = Search.session(entityManager.getEntityManagerFactory().createEntityManager());
        this.queryConstructor = queryConstructor;
    }

    public SearchResult<Product> findProducts(T config) {
        return searchSession.search(Product.class)
                .where(f -> f.bool().must(queryConstructor.search(f, config)))
                .fetch(config.getShift(), config.getPageSize());
    }

    public SearchResult<Product> findProductsByOptions(T config) {
        return searchSession.search(Product.class)
                .where(f -> f.bool().must(queryConstructor.searchByOptions(f, config)))
                .fetch(config.getShift(), config.getPageSize());
    }

    public List<List<OptionValue>> findOptions(T config) {
        return searchSession.search(Product.class)
                .select(f -> f.field("optionValue", OptionValue.class).multi())
                .where(f -> f.bool().must(queryConstructor.searchByOptions(f, config)))
                .fetchAllHits();
    }

    public List<Double> findPrices(T config) {
        return searchSession.search(Product.class)
                .select(f -> f.field("price", Double.class))
                .where(f -> f.bool().must(queryConstructor.search(f, config)))
                .fetchHits(config.getShift(), config.getPageSize());
    }

}
