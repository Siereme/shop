package com.shop.productcatalogserver.service.main;

import com.shop.productcatalogserver.dto.main.MainConfigDTO;
import com.shop.productcatalogserver.dto.main.MainResponse;

public interface IMainService {
    MainResponse getByConfig(MainConfigDTO config);
}
