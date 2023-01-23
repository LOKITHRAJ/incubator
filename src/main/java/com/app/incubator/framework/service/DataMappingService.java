package com.app.incubator.framework.service;

import com.app.incubator.framework.domain.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DataMappingService {

    public void map (Configuration configuration) {

        if (!configuration.getStatus().isValid()) {
            return;
        }

        configuration.getTasks().forEach(task -> {

            String responsePayload = task.getResponseMapping().getResponsePayload();

            Map<String, String> columnMeta =  task.getResponseMapping().getExcelExtraction().getColumnsMeta();

            DocumentContext jsonContext = JsonPath.parse(responsePayload);

            Map<String, Object> kv = new LinkedHashMap<>();
            for (String key : columnMeta.keySet()) {
                kv.put(key, jsonContext.read(columnMeta.get(key)));
            }

            task.getResponseMapping().getExcelExtraction().getColumnResult().addAll(Arrays.asList(kv));
        });
    }
}
