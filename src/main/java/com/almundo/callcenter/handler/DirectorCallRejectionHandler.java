package com.almundo.callcenter.handler;


import com.almundo.callcenter.dispatcher.CallTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Component("directorCallRejectionHandler")
public class DirectorCallRejectionHandler implements RejectedExecutionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DirectorCallRejectionHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(r instanceof CallTask){
            //TODO retry call
            CallTask callTask = (CallTask) r;
            logger.info("Call %d Rejected. All workers are bussy", callTask.getCall().getId());
        }
    }
}
