package com.almundo.callcenter.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class OperatorWorker implements Worker{

    private static final Logger logger = LoggerFactory.getLogger(PhoneCall.class);

    private int id;

    public OperatorWorker(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void answerCall(Call call) {
        int callTime = ThreadLocalRandom.current() .nextInt(5000, 10000);

        call.startCall();
        logger.info(String.format("Call %d answered by Operator %d", call.getId(), this.id));
        try {
            Thread.sleep(callTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        call.endCall();
    }
}
