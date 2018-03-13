package com.almundo.callcenter.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


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
        Optional<Call> optionalCall = Optional.ofNullable(call);

        optionalCall.ifPresent( c -> {
                    int callTime = ThreadLocalRandom.current().nextInt(5000, 10000);
                    c.startCall();
                    logger.info("Call {} answered by Director {}", c.getId(), this.id);
                    try {
                        Thread.sleep(callTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    c.endCall();
        });
    }
}
