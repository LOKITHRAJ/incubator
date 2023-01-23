package com.app.incubator.framework.thread;

import com.app.incubator.framework.domain.Configuration;
import com.app.incubator.framework.client.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service
public class TaskExecutorService {

    @Autowired
    RestClientService restClientService;

    public void execute(Configuration configuration) {

        if (!configuration.getStatus().isValid()) {
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<Future<String>>();

        configuration.getTasks().forEach(task -> {
            list.add(executor.submit(() -> {
                String response = restClientService.getForResponseEntity(task.getRequestUrl());
                task.getResponseMapping().setResponsePayload(response);
                return response;
            }));
        });

        for (Future<String> future : list) {
            try {
                System.out.println("::" + new Date() + "::" + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
