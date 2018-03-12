package com.almundo.callcenter.handler;

import com.almundo.callcenter.dispatcher.CallTask;
import com.almundo.callcenter.dispatcher.WorkerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Component("supervisorCallRejectionHandler")
public class SupervisorCallRejectionHandler implements RejectedExecutionHandler {

    @Autowired
    @Qualifier("directorExecutor")
    private ExecutorService directorExecutor;

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(r instanceof CallTask){
            CallTask rejectedCallTask = (CallTask) r;
            this.directorExecutor.execute(new CallTask(rejectedCallTask.getCall(), WorkerType.DIRECTOR));
        }
    }
}
