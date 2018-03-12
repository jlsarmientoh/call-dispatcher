package com.almundo.callcenter.dispatcher;


import com.almundo.callcenter.core.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class CallDispatcher implements Dispatcher {

    @Autowired
    @Qualifier("operatorExecutor")
    ExecutorService operatorExecutor;

    @Override
    public void dispatchCall(Call call) {
        this.operatorExecutor.execute(new CallTask(call, WorkerType.OPERATOR));
    }
}
