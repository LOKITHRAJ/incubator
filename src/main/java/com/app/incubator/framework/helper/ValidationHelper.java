package com.app.incubator.framework.helper;

import com.app.incubator.controller.DemoController;
import com.app.incubator.framework.domain.Configuration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.Set;

@Service
public class ValidationHelper {
    public void validateTemplate(Configuration configuration) {
    }

    public void setStatus(Configuration configuration) {
    }

    public void schemaValidation(Configuration configuration) {

        try {
            InputStream inputStream = DemoController.class.getClassLoader().getResourceAsStream("schema/config-schema.json");
            JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4).getSchema(inputStream);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
            JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(configuration));

            Set<ValidationMessage> errors = schema.validate(jsonNode);

            configuration.getStatus().getStatusMessage().put("schema_errors", errors);
            configuration.getStatus().setValid(CollectionUtils.isEmpty(errors) ? true: false);

        } catch(Exception e) {
            e.printStackTrace();
            configuration.getStatus().getStatusMessage().put("Exception", e.getMessage());
            configuration.getStatus().setValid(false);
        }

    }
}
