package com.app.incubator.framework.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Configuration {

    private String name;

    private String version;

    private String templateName;

    private ExecutionType executionType;

    private List<Task> tasks = new ArrayList<>();

    private Status status = new Status();

}
