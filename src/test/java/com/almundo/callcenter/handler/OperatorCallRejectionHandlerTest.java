package com.almundo.callcenter.handler;

import com.almundo.callcenter.core.PhoneCall;
import com.almundo.callcenter.dispatcher.CallTask;
import com.almundo.callcenter.dispatcher.WorkerType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorCallRejectionHandlerTest {

    @Mock
    private ExecutorService executorService;

    @InjectMocks
    OperatorCallRejectionHandler operatorCallRejectionHandler;

    @Test
    public void rejectedExecutionNotCallTaskType() throws Exception {

        Runnable noCallTaskType = new Runnable() {
            @Override
            public void run() {}
        };
        this.operatorCallRejectionHandler.rejectedExecution(noCallTaskType, null);

        Mockito.verify(executorService, Mockito.times(0)).execute(noCallTaskType);
    }

    @Test
    public void rejectedExecutionCallTaskType() throws Exception {

        CallTask callTask = new CallTask(new PhoneCall(1), WorkerType.OPERATOR);
        this.operatorCallRejectionHandler.rejectedExecution(callTask, null);

        Mockito.verify(executorService, Mockito.times(1)).execute(any(CallTask.class));
    }

}