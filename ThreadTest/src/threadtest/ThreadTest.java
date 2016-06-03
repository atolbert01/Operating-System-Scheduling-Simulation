package threadtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Aaron Tolbert-Smith
 */
public class ThreadTest {

    public static void FCFS(ArrayList<ThreadObject> threadObjects) {

        ArrayList<Thread> FCFSList = new ArrayList();

        for (int i = 0; i < threadObjects.size(); i++) {

            Thread thread;

            if (threadObjects.get(i).getType().equalsIgnoreCase("IO")) {
                thread = new Thread(new IOThread(threadObjects.get(i).getName()));
                FCFSList.add(thread);
            } else if (threadObjects.get(i).getType().equalsIgnoreCase("CPU")) {
                thread = new Thread(new CPUThread(threadObjects.get(i).getName()));
                FCFSList.add(thread);
            } else if (threadObjects.get(i).getType().equalsIgnoreCase("INTER")) {
                thread = new Thread(new IntermediateThread(threadObjects.get(i).getName()));
                FCFSList.add(thread);
            }
        }

        Thread currentThread = new Thread();

        while (!FCFSList.isEmpty()) {
            try {

                FCFSList.get(0).start();
                currentThread = FCFSList.iterator().next();

                currentThread.join();

                FCFSList.remove(0);

            } catch (InterruptedException ex) {
            }
        }

    }

    public static void SJF(ArrayList<ThreadObject> threadObjects) {

        ArrayList<Thread> SJFList = new ArrayList();

        Collections.sort(threadObjects, (t1, t2) -> (int) (t1.getBurst() - t2.getBurst()));

        for (int i = 0; i < threadObjects.size(); i++) {

            Thread thread;

            if (threadObjects.get(i).getType().equalsIgnoreCase("IO")) {
                thread = new Thread(new IOThread(threadObjects.get(i).getName()));
                SJFList.add(thread);
            } else if (threadObjects.get(i).getType().equalsIgnoreCase("CPU")) {
                thread = new Thread(new CPUThread(threadObjects.get(i).getName()));
                SJFList.add(thread);
            } else if (threadObjects.get(i).getType().equalsIgnoreCase("INTER")) {
                thread = new Thread(new IntermediateThread(threadObjects.get(i).getName()));
                SJFList.add(thread);
            }
        }

        Thread currentThread = new Thread();

        while (!SJFList.isEmpty()) {
            try {

                SJFList.get(0).start();
                currentThread = SJFList.iterator().next();

                currentThread.join();

                SJFList.remove(0);

            } catch (InterruptedException ex) {
            }
        }

    }

    public static void RoundRobin(ArrayList<ThreadObject> threadObjects) {

        ArrayList<Thread> RRList = new ArrayList();

        for (int i = 0; i < threadObjects.size(); i++) {

            Thread thread;

            if (threadObjects.get(i).getType().equalsIgnoreCase("IO")) {
                thread = new Thread(new IOThread(threadObjects.get(i).getName()));
                RRList.add(thread);
            } else if (threadObjects.get(i).getType().equalsIgnoreCase("CPU")) {
                thread = new Thread(new CPUThread(threadObjects.get(i).getName()));
                RRList.add(thread);
            } else if (threadObjects.get(i).getType().equalsIgnoreCase("INTER")) {
                thread = new Thread(new IntermediateThread(threadObjects.get(i).getName()));
                RRList.add(thread);
            }
        }
        
        Thread currentThread;
        ArrayList<Thread> outputList = new ArrayList();
        String threadState;
        boolean isExecuting = false;

        while (!RRList.isEmpty()) {
            long end = System.currentTimeMillis() + 60;

            outputList.add(RRList.remove(0));
            while (!outputList.isEmpty()) {
                currentThread = outputList.get(0);
                threadState = currentThread.getState().toString();
                System.out.println(currentThread.toString() + ": " + threadState);

                if (threadState.equalsIgnoreCase("NEW")) {
                    currentThread.start();
                    isExecuting = true;
                } else if (threadState.equalsIgnoreCase("RUNNABLE")) {

                    while (System.currentTimeMillis() < end) {
                        isExecuting = true;
                    }
                } else if (threadState.equalsIgnoreCase("TERMINATED")) {
                    outputList.remove(currentThread);
                    RRList.remove(currentThread);
                    break;
                }

                if (isExecuting) {
                    currentThread.resume();
                } else {
                    currentThread.suspend();
                }
                outputList.remove(currentThread);
                RRList.add(currentThread);
            }

        }
    }

    public static void main(String[] args) {

        ArrayList<ThreadObject> threadObjects = new ArrayList();

        // Initializing the Runnable objects
        //
        // Burst is hard coded based on elapsed times from earlier tests
        // I attempted a method of grabbing the elapsed times from the current
        // test and using that as my burst times for SJF algorithm, but have
        // gotten more accurate results the original way.
        //
        // Type is used to determine which TYPE of ThreadObject to create
        // from a list of all ThreadObjects
        IOThread ioObject1 = new IOThread("ioObject1");
        IOThread ioObject2 = new IOThread("ioObject2");
        IOThread ioObject3 = new IOThread("ioObject3");
        ioObject1.setBurst(154);
        ioObject2.setBurst(129);
        ioObject3.setBurst(142);
        ioObject1.setType("IO");
        ioObject2.setType("IO");
        ioObject3.setType("IO");
        ioObject1.setName("IOThread1");
        ioObject2.setName("IOThread2");
        ioObject3.setName("IOThread3");

        CPUThread cpuObject1 = new CPUThread("cpuObject1");
        CPUThread cpuObject2 = new CPUThread("cpuObject2");
        CPUThread cpuObject3 = new CPUThread("cpuObject3");
        cpuObject1.setBurst(31);
        cpuObject2.setBurst(20);
        cpuObject3.setBurst(19);
        cpuObject1.setType("CPU");
        cpuObject2.setType("CPU");
        cpuObject3.setType("CPU");
        cpuObject1.setName("CPUThread1");
        cpuObject2.setName("CPUThread2");
        cpuObject3.setName("CPUThread3");

        IntermediateThread interObject1 = new IntermediateThread("interObject1");
        IntermediateThread interObject2 = new IntermediateThread("interObject2");
        IntermediateThread interObject3 = new IntermediateThread("interObject3");
        interObject1.setBurst(47);
        interObject2.setBurst(92);
        interObject3.setBurst(85);
        interObject1.setType("INTER");
        interObject2.setType("INTER");
        interObject3.setType("INTER");
        interObject1.setName("interThread1");
        interObject2.setName("interThread2");
        interObject3.setName("interThread3");

        // Adding all 9 objects to ArrayList
        threadObjects.add(0, ioObject1);
        threadObjects.add(1, ioObject2);
        threadObjects.add(2, ioObject3);
        threadObjects.add(3, cpuObject1);
        threadObjects.add(4, cpuObject2);
        threadObjects.add(5, cpuObject3);
        threadObjects.add(6, interObject1);
        threadObjects.add(7, interObject2);
        threadObjects.add(8, interObject3);

        // First-Come-First-Serve method.
        FCFS(threadObjects);

        // Shortest-Job-First method.
        SJF(threadObjects);

        // Round Robin method.
        RoundRobin(threadObjects);
    }

}
