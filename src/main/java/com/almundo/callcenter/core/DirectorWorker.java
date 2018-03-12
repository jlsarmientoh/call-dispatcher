package com.almundo.callcenter.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yorch on 11/03/18.
 */
public class DirectorWorker implements Worker {

    private static final Logger logger = LoggerFactory.getLogger(DirectorWorker.class);

    private final int id;

    public DirectorWorker(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void answerCall(Call call) {
        int callTime = ThreadLocalRandom.current() .nextInt(5000, 10000);

        call.startCall();
        logger.info(String.format("Call %d answered by Supervisor %d", call.getId(), this.id));
        try {
            Thread.sleep(callTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        call.endCall();
    }
}
