package com.app.incubator.framework.domain;

import lombok.Data;

@Data
public class Task {

    private String name;
    private Integer priority;
    private TaskType taskType;
    private String requestUrl;
    private ResponseMapping responseMapping;

}
