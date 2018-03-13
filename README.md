# Call Dispatcher

For technical deatils, please go to: [Wiki](https://github.com/jlsarmientoh/call-dispatcher/wiki).

## Pre-requisites

* Java 8
* Maven 3.3.9 or higher
* Git

# Before Starting

Each call can be traced by logging entries in the console as follows:

`INFO 15387 --- [pool-4-thread-1] c.a.c.c.PhoneCall : Call 1 started at 2018-03-12T22:54:01.505-0500`

`INFO 15387 --- [pool-4-thread-1] c.a.c.c.PhoneCall  : Call 1 answered by Operator 17`

`INFO 15387 --- [pool-4-thread-1] c.a.c.c.PhoneCall  : Call 1 ended at 2018-03-12T22:54:10.685-0500`

## Package and Execute

Once you clone this repository on your local machine, you can execute this project as a stand alone application as follows:

_`mvn package && java -jar target/call-dispatcher-1.0-SNAPSHOT.jar`_

_Note: Make sure you are placed at the root path of the project.  Where the pom.xml file is located at._

## Call Forwarding Rules

A given call will be answered by the first available instance of worker. There's a queue for each of the instances:  Operator, Supervisor and Director, calls are forwarded based on the following rules:

1. An incoming call is always forwarded first to operator queue.
2. In case the operator queue is full or no Operator available, then the call is forwarded to supervisor queue.
3. In case the supervisor queue is full or no Supervisor available, then the call is forwarded to director queue.
4. In case the director queue is full or no Director available, then the call is rejected.

### Tracking Call Forwarding

In the console you will be able to track a call and details of forwarding. The following are full samples of a calls that are forwarded:

1.  To operator queue and gets answred by an Operator:

`INFO 16206 --- [pool-4-thread-1] c.a.c.c.PhoneCall: Call 1 started at 2018-03-12T23:00:15.984-0500`

`INFO 16206 --- [pool-4-thread-1] c.a.c.c.PhoneCall: Call 1 answered by Operator 17`

`INFO 16206 --- [pool-4-thread-1] c.a.c.c.PhoneCall: Call 1 ended at 2018-03-12T23:00:24.438-0500`

2.  To supervisor queue and gets answred by a Supervisor

`INFO 16206 --- [           main] c.a.c.h.OperatorCallRejectionHandler: Call 21 forwarded to Supervisor`

`INFO 16206 --- [pool-3-thread-1] c.a.c.c.PhoneCall: Call 21 started at 2018-03-12T23:00:16.025-0500`

`INFO 16206 --- [pool-3-thread-1] c.a.c.c.SupervisorWorker: Call 21 answered by Supervisor 27`

`INFO 16206 --- [pool-3-thread-1] c.a.c.c.PhoneCall: Call 21 ended at 2018-03-12T23:00:22.413-0500`

3.  To director queue and gets answred by a Director

`INFO 16206 --- [           main] c.a.c.h.OperatorCallRejectionHandler: Call 26 forwarded to Supervisor`

`INFO 16206 --- [           main] c.a.c.h.SupervisorCallRejectionHandler: Call 26 forwarded to Director`

`INFO 16206 --- [pool-2-thread-1] c.a.c.c.PhoneCall: Call 26 started at 2018-03-12T23:00:22.218-0500`

`INFO 16206 --- [pool-2-thread-1] c.a.c.c.DirectorWorker: Call 26 answered by Director 29`

`INFO 16206 --- [pool-2-thread-1] c.a.c.c.PhoneCall: Call 26 ended at 2018-03-12T23:00:29.347-0500`


4.  Along all the queues until gets rejected

`INFO 16206 --- [           main] c.a.c.h.OperatorCallRejectionHandler     : Call 29 forwarded to Supervisor`

`INFO 16206 --- [           main] c.a.c.h.SupervisorCallRejectionHandler   : Call 29 forwarded to Director`

`INFO 16206 --- [           main] c.a.c.h.DirectorCallRejectionHandler     : Call 29 Rejected. All workers are bussy`

**_Note: Due to concurrency, this log messages migth not be seen ins the same sequence_**

## Unit tests

For unit test execution just go to the root project path and run `mvn test`.

**_CallDispatcherTest.dispatchMultipleCalls_** simulates 30 concurrent calls. Use this unit test to check overall application behavior E2E test.

_Note: Feel free to modify the number on calls set in this test_
