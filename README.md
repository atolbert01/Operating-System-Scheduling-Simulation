# Operating-System-Scheduling-Simulation
This Java application generates a variety of runnable objects and creates threads to execute each using implementations of First-Come-First-Serve, Shortest-Job-First, and Round Robin algorithms.

CPUThread – Calculates the first 1000 prime numbers.
IOThread – Writes to System.out 1000 times.
IntermediateThread  - Calculates the first 1000 prime numbers and writes the result to System.out.

The runnable objects are children of the ThreadObject class which is used to initialize the burst time for each object (used in Shortest-Job-First algorithm) and is the base type used for ArrayLists of all runnable objects.

The ThreadTest class contains the main method and three methods which handle scheduling. The ThreadObjects are instantiated within the main method and added to an ArrayList <ThreadObject> which is then passed to the various scheduling methods:

FCFS() – The First-Come-First-Serve scheduler which iterates over the ThreadObject list and adds each object to a new ArrayList <Thread>. Each type of ThreadObject is cast to a new thread of the requisite type and added to the FCFSList<Thread>. The list is then iterated and each thread’s start() method is called in the proper sequence.

SJF() – The Shortest-Job-First scheduler which behaves in a similar way to the FCFS() scheduler method. The only difference is that the ThreadObject ArrayList is first sorted according to the burst time of each object. Burst time is initialized when each object is first instantiated. The burst times are hard-coded and are based on the elapsed times from an earlier run of the FCFS() method. This was chosen instead of updating burst times on the fly based on FCFS() elapsed times because it resulted in more realistic results. As this is a simulation, since it is not possible in this case to know the actual burst times beforehand, hard-coding seemed the best way forward.

RoundRobin() – The Round Robin scheduler. Iterates over the ThreadObject list as in the other two scheduler methods. However, this method differs in that it creates an additional ArrayList<Thread> outputList. The way the scheduler works is as follows:

1.	Checks RRList (the primary ArrayList containing all the threads) to see if empty.
2.	Removes the top object from RRList and adds to outputList.
3.	Executes the thread for a predetermined quantum of time.
4.	If the thread is not terminated at the end of the time slice add remove from outputList and add back to RRList.
5.	Repeat until RRList and outputList are both empty and all threads are terminated.

The RoundRobin() scheduler makes use of the Thread.suspend() and Thread.resume() methods to manage thread execution. A boolean value isExecuting is used to determine at what times the thread should be suspended and when to resume.

In addition to the above mentioned classes the OutputWriter class is used to write elapsed times to a text document.
