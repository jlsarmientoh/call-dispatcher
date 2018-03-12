package com.almundo.callcenter.dispatcher;

import com.almundo.callcenter.core.Call;
import com.almundo.callcenter.core.PhoneCall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CallDispatcherTest {

    @Autowired
    private Dispatcher dispatcher;

    @Test
    public void dispatchMultipleCalls() throws Exception {

        for(int i = 1; i <= 10; i++){
            dispatcher.dispatchCall(new PhoneCall(i));
        }

        Thread.sleep(20000);
    }

    @Test
    public void dispathSingleCall() throws Exception{
        Call singleCall = Mockito.spy(new PhoneCall(1));
        dispatcher.dispatchCall(singleCall);

        Thread.sleep(10000);

        Mockito.verify(singleCall, Mockito.times(1)).startCall();
        Mockito.verify(singleCall, Mockito.times(1)).endCall();
    }

    @Test
    public void dispathNullCall() throws Exception{
        Call singleCall = Mockito.spy(new PhoneCall(1));
        dispatcher.dispatchCall(null);

        Thread.sleep(10000);

        Mockito.verify(singleCall, Mockito.times(0)).startCall();
        Mockito.verify(singleCall, Mockito.times(0)).endCall();
    }

}