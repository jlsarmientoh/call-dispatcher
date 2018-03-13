package com.almundo.callcenter.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupervisorWorkerTest {

    private Worker supervisorWorker = new SupervisorWorker(1);

    @Test
    public void answerCall() throws Exception {
        Call call = new PhoneCall(1);
        Call spyCall = spy(call);

        this.supervisorWorker.answerCall(spyCall);

        verify(spyCall, times(1)).startCall();
        verify(spyCall, times(1)).endCall();

    }

    @Test
    public void answerNullCall() throws Exception {
        Call call = new PhoneCall(1);
        Call spyCall = spy(call);

        this.supervisorWorker.answerCall(null);

        verify(spyCall, times(0)).startCall();
        verify(spyCall, times(0)).endCall();
    }

}