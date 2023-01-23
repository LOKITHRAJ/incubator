package com.app.incubator.framework.domain;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ResultColumnMap {

    Map<String, Object> map = new LinkedHashMap<>();

}
