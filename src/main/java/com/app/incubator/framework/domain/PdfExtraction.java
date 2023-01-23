package com.app.incubator.framework.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PdfExtraction {

    private Map<String, String> values = new HashMap<>();

}
