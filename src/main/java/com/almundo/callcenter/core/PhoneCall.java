package com.almundo.callcenter.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class PhoneCall implements Call {

    private static final Logger logger = LoggerFactory.getLogger(PhoneCall.class);

    private final int callId;

    public PhoneCall(int callId) {
        this.callId = callId;
    }

    @Override
    public final int getId() {
        return callId;
    }

    @Override
    public void startCall() {
        logger.info(String.format("Call %d started at %s",this.callId, new Date()));
    }

    @Override
    public void endCall() {
        logger.info(String.format("Call %d ended at %s",this.callId, new Date()));
    }
}
