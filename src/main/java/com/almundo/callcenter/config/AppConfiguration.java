package com.almundo.callcenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class AppConfiguration {

    @Value("${dispatcher.operatorPoolSize}")
    private int operatorPoolSize;

    @Value("${dispatcher.supervisorPoolSize}")
    private int supervisorPoolSize;

    @Value("${dispatcher.directorPoolSize}")
    private int directorPoolSize;

    @Bean("operatorExecutor")
    public ExecutorService createOperatorExecutor(
            @Autowired
            @Qualifier("operatorCallRejectionHandler") RejectedExecutionHandler handler){
        ExecutorService executorService = new ThreadPoolExecutor(this.operatorPoolSize, this.operatorPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(this.operatorPoolSize));
        ((ThreadPoolExecutor)executorService).setRejectedExecutionHandler(handler);

        return executorService;
    }

    @Bean("supervisorExecutor")
    public ExecutorService createSupervisorExecutor(
            @Autowired
            @Qualifier("supervisorCallRejectionHandler") RejectedExecutionHandler handler){
        ExecutorService executorService = new ThreadPoolExecutor(this.supervisorPoolSize, this.supervisorPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(this.supervisorPoolSize));
        ((ThreadPoolExecutor)executorService).setRejectedExecutionHandler(handler);

        return executorService;
    }

    @Bean("directorExecutor")
    public ExecutorService createDirectorExecutor(@Autowired @Qualifier("directorCallRejectionHandler") RejectedExecutionHandler handler){
        ExecutorService executorService = new ThreadPoolExecutor(this.directorPoolSize, this.directorPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(this.directorPoolSize));
        ((ThreadPoolExecutor)executorService).setRejectedExecutionHandler(handler);

        return executorService;

    }
}
