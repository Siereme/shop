package com.shop.productcatalogserver.service.search;

import com.shop.productcatalogserver.dto.search.RangePriceDTO;
import com.shop.productcatalogserver.dto.search.SearchDTO;
import com.shop.productcatalogserver.dto.search.SearchResponse;
import com.shop.productcatalogserver.model.product.Product;
import com.shop.productcatalogserver.model.product.option.OptionValue;
import com.shop.productcatalogserver.repository.search.SearchQueryService;
import com.shop.productcatalogserver.service.search.constructor.impl.SearchResponseBuilder;
import org.hibernate.search.engine.search.query.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
