package app.config.serialize;

import app.model.dto.search.OptionValueDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class OptionValueDTODeserializer extends JsonDeserializer<OptionValueDTO> {

    @Override
    public OptionValueDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String json = jsonParser.getText();
        jsonParser.nextToken();
        String value = jsonParser.getText();
        jsonParser.nextToken();
        boolean checked = jsonParser.getBooleanValue();
        jsonParser.nextToken();
        json = jsonParser.getText();

        return new OptionValueDTO(value, checked);
    }

}
