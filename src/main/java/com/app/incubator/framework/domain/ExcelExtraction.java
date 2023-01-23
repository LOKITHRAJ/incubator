package com.app.incubator.framework.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class ExcelExtraction {

    private Map<String, String> columnsMeta = new LinkedHashMap<>();

    private List<Map<String, Object>> columnResult = new ArrayList<>();

}
