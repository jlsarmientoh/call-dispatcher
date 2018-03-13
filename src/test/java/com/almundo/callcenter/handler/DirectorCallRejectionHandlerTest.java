package com.almundo.callcenter.handler;


import com.almundo.callcenter.core.PhoneCall;
import com.almundo.callcenter.dispatcher.CallTask;
import com.almundo.callcenter.dispatcher.WorkerType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectorCallRejectionHandlerTest {

    @InjectMocks
    DirectorCallRejectionHandler directorCallRejectionHandler;

    @Test
    public void rejectedExecutionNotCallTaskType() throws Exception {

        Runnable noCallTaskType = new Runnable() {
            @Override
            public void run() {}
        };
        this.directorCallRejectionHandler.rejectedExecution(noCallTaskType, null);

        //Mockito.verify(executorService, Mockito.times(0)).execute(noCallTaskType);
    }

    @Test
    public void rejectedExecutionCallTaskType() throws Exception {

        CallTask callTask = new CallTask(new PhoneCall(1), WorkerType.DIRECTOR);
        this.directorCallRejectionHandler.rejectedExecution(callTask, null);

        //Mockito.verify(executorService, Mockito.times(1)).execute(any(CallTask.class));
    }

}