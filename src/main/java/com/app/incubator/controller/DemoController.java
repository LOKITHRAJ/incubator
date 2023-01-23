package com.app.incubator.controller;

import com.app.incubator.framework.domain.Configuration;
import com.app.incubator.framework.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    ReportingService reportingService;

    @PostMapping("/v1/config")
    Configuration getConfig(@RequestBody Configuration configuration) {
        return configuration;
    }

    @PostMapping("/v1/apply/config")
    Configuration applyConfig(@RequestBody Configuration configuration) {
        reportingService.apply(configuration);
        return configuration;
    }

}
