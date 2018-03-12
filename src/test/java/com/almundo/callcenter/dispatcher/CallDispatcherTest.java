package com.almundo.callcenter.dispatcher;

import com.almundo.callcenter.core.PhoneCall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CallDispatcherTest {

    @Autowired
    private Dispatcher dispatcher;
    @Test
    public void dispatchCall() throws Exception {

        for(int i = 1; i <= 100; i++){
            dispatcher.dispatchCall(new PhoneCall(i));
        }
    }

}