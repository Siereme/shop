package app.service.search;

import app.constructor.search.impl.SearchResponseBuilder;
import app.model.dto.search.RangePriceDTO;
import app.model.dto.search.SearchCategoryDTO;
import app.model.dto.search.SearchCategoryResponse;
import app.model.product.Product;
import app.model.product.option.OptionValue;
import app.service.category.CategoryService;
import app.service.search.query.SearchCategoryQueryService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.search.engine.search.query.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchCategoryService implements ISearchService<SearchCategoryDTO> {

    @Autowired
    private SearchCategoryQueryService queryHandler;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SearchResponseBuilder<SearchCategoryResponse> responseBuilder;

    @Override
    public SearchCategoryResponse search(SearchCategoryDTO config) {
        SearchCategoryResponse response = categoryService.getBySearchConfig(config);
        config.getCategory().setPath(response.getCategory().getPath());

        int shift = (config.getPage() - 1) * config.getPageSize();
        config.setShift(shift);

        SearchResult<Product> result = queryHandler.findProducts(config);
        List<List<OptionValue>> options = queryHandler.findOptions(config);

        return responseBuilder
                .create(response)
                .setProducts(result.hits())
                .setRangePrice(config.getRangePrice())
                .setOptions(options)
                .setPage(config.getPage(), result.total().hitCount() / config.getPageSize())
                .build();
    }

    @Override
    public SearchCategoryResponse searchByOptions(SearchCategoryDTO config) {
        String path = config.getCategory().getPath()
                .substring(0, StringUtils.ordinalIndexOf(config.getCategory().getPath(), "/", config.getCategory().getDepth()) + 1);
        config.getCategory().setPath(path);

        int shift = (config.getPage() - 1) * config.getPageSize();
        config.setShift(shift);

        SearchResult<Product> result = queryHandler.findProductsByOptions(config);
        List<List<OptionValue>> options = queryHandler.findOptions(config);
        RangePriceDTO rangePrice = config.getRangePrice();
        List<Double> prices = new ArrayList<>();
        if (rangePrice != null && rangePrice.isValid()) {
            config.setRangePrice(null);
            prices.addAll(queryHandler.findPrices(config));
        }

        return responseBuilder
                .create(new SearchCategoryResponse())
                .setProducts(result.hits())
                .setFullRangePrices(prices)
                .setRangePrice(rangePrice)
                .setCheckedOptions(config.getOptions())
                .setOptions(options)
                .setPage(config.getPage(), result.total().hitCount() / config.getPageSize())
                .build();
    }

}
