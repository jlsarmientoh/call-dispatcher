package com.almundo.callcenter.handler;

import com.almundo.callcenter.dispatcher.CallTask;
import com.almundo.callcenter.dispatcher.WorkerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


@Component("operatorCallRejectionHandler")
public class OperatorCallRejectionHandler implements RejectedExecutionHandler {

    @Autowired
    @Qualifier("supervisorExecutor")
    private ExecutorService supervisorExecutor;

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(r instanceof CallTask){
            CallTask rejectedCallTask = (CallTask) r;
            this.supervisorExecutor.execute(new CallTask(rejectedCallTask.getCall(), WorkerType.SUPERVISOR));
        }
    }
}
