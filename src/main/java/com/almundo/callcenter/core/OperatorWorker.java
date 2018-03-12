package com.almundo.callcenter.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class OperatorWorker implements Worker{

    private static final Logger logger = LoggerFactory.getLogger(PhoneCall.class);

    private final int id;

    public OperatorWorker(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void answerCall(Call call) {
        Optional<Call> optionalCall = Optional.of(call);

        optionalCall.ifPresent( c -> {
            int callTime = ThreadLocalRandom.current() .nextInt(5000, 10000);
            c.startCall();
            logger.info(String.format("Call %d answered by Operator %d", c.getId(), this.id));
            try {
                Thread.sleep(callTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c.endCall();
        });
    }
}
