package threadtest;

import java.io.IOException;

/**
 *
 * Aaron Tolbert-Smith
 */
public class IOThread extends ThreadObject implements Runnable {

    private String threadName = "";

    private long startTime;
    private long endTime;
    private long elapsedTime;

    public IOThread(String threadName) {
        this.threadName = threadName;
        //Thread runner = new Thread(this, threadName);
    }
    
    public long getElapsedTime(){
        return elapsedTime;
    }

    @Override
    public void run() {

        startTime = System.currentTimeMillis();

        //Printing to the console 1000 times. Nothing fancy.
        for (int i = 0; i < 1000; i++) {
            System.out.println(threadName + ": " + i);
        }

        endTime = System.currentTimeMillis();
        elapsedTime = (endTime - startTime);
        System.out.println(threadName + " Elapsed Time: " + elapsedTime + " milliseconds");

        try {
            OutputWriter.getInstance().writeToFile(threadName, elapsedTime);
        } catch (IOException ex) {
            System.out.println("There's been an IO Exception!");
        }

    }

}
