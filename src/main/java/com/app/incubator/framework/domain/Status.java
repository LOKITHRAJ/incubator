package com.app.incubator.framework.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Status {

    private EState state;

    private String code;

    private Map<String, Object> statusMessage = new HashMap<>();

    private boolean isValid;
}
