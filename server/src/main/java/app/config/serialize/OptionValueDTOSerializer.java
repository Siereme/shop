package app.config.serialize;

import app.model.dto.search.OptionValueDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class OptionValueDTOSerializer extends JsonSerializer<OptionValueDTO> {

    @Override
    public void serialize(OptionValueDTO optionValueDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(optionValueDTO.getValue());
        jsonGenerator.writeBoolean(optionValueDTO.isChecked());
        jsonGenerator.writeEndObject();

    }

}
