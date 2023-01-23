package com.app.incubator.framework.service;

import com.app.incubator.framework.domain.Configuration;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    public void notify(Configuration configuration) {

        if (!configuration.getStatus().isValid()) {
            return;
        }
    }

}
