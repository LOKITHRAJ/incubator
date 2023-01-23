package com.app.incubator.framework.service;

import com.app.incubator.framework.domain.Configuration;
import com.app.incubator.framework.helper.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    ValidationHelper validationHelper;

    public void validate(Configuration configuration) {

        // 1. Basic Schema Validation
        validationHelper.schemaValidation(configuration);


        // 2. Template Validation
        validationHelper.validateTemplate(configuration);


        // n... SetValid Template
        validationHelper.setStatus(configuration);
    }

}
