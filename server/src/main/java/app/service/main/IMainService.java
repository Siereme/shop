package app.service.main;

import app.model.dto.main.MainConfigDTO;
import app.model.dto.main.MainResponse;

public interface IMainService {
    MainResponse getByConfig(MainConfigDTO config);
}
