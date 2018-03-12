package com.almundo.callcenter.dispatcher;

import com.almundo.callcenter.core.*;

import java.security.InvalidParameterException;


public class CallTask implements Runnable {

    private final Call call;
    private final WorkerType workerType;

    public Call getCall() {
        return call;
    }

    public CallTask(Call call, WorkerType workerType) {
        this.call = call;
        this.workerType = workerType;
    }

    @Override
    public void run() {
        Worker worker;
        switch (this.workerType){
            case OPERATOR:{
                worker = new OperatorWorker((int)Thread.currentThread().getId());
                break;
            }
            case SUPERVISOR:{
                worker = new SupervisorWorker((int)Thread.currentThread().getId());
                break;
            }
            case DIRECTOR:{
                worker = new DirectorWorker((int)Thread.currentThread().getId());
                break;
            }
            default: throw new InvalidParameterException();
        }

        worker.answerCall(call);
    }
}
