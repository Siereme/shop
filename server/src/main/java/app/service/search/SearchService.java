package app.service.search;

import app.constructor.search.impl.SearchResponseBuilder;
import app.model.dto.search.RangePriceDTO;
import app.model.dto.search.SearchDTO;
import app.model.dto.search.SearchResponse;
import app.model.product.Product;
import app.model.product.option.OptionValue;
import app.service.search.query.SearchQueryService;
import org.hibernate.search.engine.search.query.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService implements ISearchService<SearchDTO> {

    @Autowired
    private SearchQueryService queryHandler;
    @Autowired
    private SearchResponseBuilder<SearchResponse> responseBuilder;

    @Override
    public SearchResponse search(SearchDTO config) {
        int shift = (config.getPage() - 1) * config.getPageSize();
        config.setShift(shift);

        SearchResult<Product> result = queryHandler.findProducts(config);
        List<List<OptionValue>> options = queryHandler.findOptions(config);

        return responseBuilder
                .create(new SearchResponse())
                .setProducts(result.hits())
                .setRangePrice(config.getRangePrice())
                .setOptions(options)
                .setPage(config.getPage(), result.total().hitCount() / config.getPageSize())
                .build();
    }

    @Override
    public SearchResponse searchByOptions(SearchDTO config) {
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
                .create(new SearchResponse())
                .setProducts(result.hits())
                .setFullRangePrices(prices)
                .setRangePrice(rangePrice)
                .setCheckedOptions(config.getOptions())
                .setOptions(options)
                .setPage(config.getPage(), result.total().hitCount() / config.getPageSize())
                .build();
    }

}
