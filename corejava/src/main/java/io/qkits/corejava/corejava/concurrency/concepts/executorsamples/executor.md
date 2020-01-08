# Executor Service

1. Task - > implement Runnable/Callable interface
2. Executor -> ThreadPoolExecutor/ScheduledThreadPollExecutor
3. Async - > Future/Future Task

## ThreadPoolExecutor

- corePool
- maximumPool
- BlockingQueue : store tasks
- RejectedExecutionHandler

## FixedThreadPool

- LinkedBlockingQueue for task 
- RejectedExecutionHandler is not invoked

What's is linked blocking queue??

## CachedThreadPool

- SynchronousQueue<Runnable>