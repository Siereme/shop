package app.service.search;

import app.model.dto.search.ISearchDTO;
import app.model.dto.search.ISearchResponse;

public interface ISearchService<T extends ISearchDTO> {

    ISearchResponse search(T config);

    ISearchResponse searchByOptions(T config);

}
