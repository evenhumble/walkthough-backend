---
title: How To Read A Thread Dump
date: 2018-06-29 21:34:04
tags:
    - ThreadDump
    - Performance
    - Tip
---

# How To Read A Thread Dump

- Thread Terminology


## Thread Terminology

- Thread/daemon Thread,
  * thread is discrete unit of concurrency managed by JVM
  * daemon thread: runs independent of other threads, killed when Runtime.exit
- Different state Threads
  * Alive thread - A running thread taht is performance some work
  * Blocked thread - attempt to enter synchronized block blocked by another thread
  * Waiting Thread - waiting for another thread to call
  * Sleep Thread - not executing because sleep is called
- Monitor
  * [reference](https://docs.oracle.com/javase/specs/jls/se10/html/jls-17.html#jls-17.1)
- Deadlock
  * [reference](https://en.wikipedia.org/wiki/Dining_philosophers_problem)
  ![img](https://dzone.com/storage/temp/9523858-deadlock-definition.png)
  ![img](https://dzone.com/storage/temp/9523858-deadlock-definition.png)
 - Livelock
  * Thread A performs and action that causes thread B to perform an action
  * Thread A still alive
  * [Reference](https://docs.oracle.com/javase/specs/jls/se10/html/jls-17.html)


[reference](https://dzone.com/articles/how-to-read-a-thread-dump)
[r2](https://dzone.com/articles/how-analyze-java-thread-dumps)

waiting/wait/block/