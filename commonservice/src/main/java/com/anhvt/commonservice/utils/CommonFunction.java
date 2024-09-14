/**
 * Copyright 2024
 * Name: CommonFunction
 */
package com.anhvt.commonservice.utils;

import com.anhvt.commonservice.common.ValidateException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Methods for validate json
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
@Slf4j
@NoArgsConstructor
public class CommonFunction {

    @SneakyThrows
    public static void jsonValidate(InputStream inputStream, String json) {
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(inputStream);
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(json);
        Set<ValidationMessage> errors = schema.validate(jsonNode);

        Map<String, String> stringSetMap = new HashMap<>();
        for (ValidationMessage error: errors) {
            log.info(error.getMessage());
            String key = formatStringValidation(error.getArguments()[0]);
            if (stringSetMap.containsKey(key)) {
                String message = stringSetMap.get(key);
                stringSetMap.put(key, message + ", " + formatStringValidation(error.getMessage()));
            } else {
                stringSetMap.put(key, formatStringValidation(error.getMessage()));
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidateException("PF01", stringSetMap, HttpStatus.BAD_REQUEST);
        }
    }

    public static String formatStringValidation(String message) {
        return message.replace("\\$.", "");
    }
}
