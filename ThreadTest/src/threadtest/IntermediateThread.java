package threadtest;

import java.io.IOException;

/**
 *
 * Aaron Tolbert-Smith
 */
public class IntermediateThread extends ThreadObject implements Runnable {

    private String threadName = "";

    private long startTime;
    private long endTime;
    private long elapsedTime;


    //the value being tested for prime status
    private int num;

    private boolean isPrime;

    
    public IntermediateThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        //This loop will find and display the first 500 primes
        for (int i = 2; i <= 501;) {

            //Chose not to use Math.sqrt(num) to terminate loop
            //to better illustrate efficiency.
            //for (int j = 2; j < Math.sqrt(num); j++) {
            for (int j = 2; j < num; j++) {

                //if isPrime exit loop and print the number
                if (num % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(threadName + " Prime: " + num);
                i++;
            }
            isPrime = true;
            num++;
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

    public String getThreadName() {
        return threadName;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

}