package com.almundo.callcenter.dispatcher;

import com.almundo.callcenter.core.PhoneCall;
import com.almundo.callcenter.exception.InvalidArgumentException;
import org.junit.Test;


public class CallTaskTest {

    @Test(expected = InvalidArgumentException.class)
    public void runInvalidWorkerType() throws Exception {
        CallTask callTask = new CallTask(new PhoneCall(1),null);
        callTask.run();
    }

    @Test
    public void runNullCall() throws Exception {
        CallTask callTask = new CallTask(null, WorkerType.DIRECTOR);
        callTask.run();
    }

}