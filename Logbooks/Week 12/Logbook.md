# Week 12

## Threads
A thread is an independent path of execution; you create one by passing a Runnable (often a lambda) to a Thread, e.g. new Thread(() -> doWork()).start(). Calling start() runs the code concurrently, while join() makes the current thread wait for it to finish.

## Synchronisation
synchronized blocks let only one thread at a time run a section of code guarded by a lock object, and ReentrantLock offers the same with more control (e.g. tryLock()). Atomic variables such as AtomicInteger provide thread-safe operations like incrementAndGet() without 
explicit locking.

## Parallel streams
Calling parallelStream() (or .parallel()) splits a stream's data across multiple threads automatically, which can speed up large, independent computations. It works best with stateless operations and no shared mutable state, and can be slower than a sequential stream for small datasets.

## Race conditions
A race condition happens when two threads read and write shared state at the same time without coordination, so the result depends on timing. For example, two threads running count++ can lose updates because the read, add and write steps interleave.

## Fork-Join
The ForkJoinPool framework splits a large task into smaller subtasks recursively (fork), runs them in parallel, then combines their results (join). Tasks extend RecursiveTask (returns a value) or RecursiveAction (no value), and idle threads "steal" work from busy ones to balance the load.