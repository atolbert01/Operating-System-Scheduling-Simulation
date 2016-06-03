package threadtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * Aaron Tolbert-Smith
 */
public class OutputWriter {

    private static final OutputWriter inst = new OutputWriter();
    File output = new File("output.txt");

    private OutputWriter() {
        super();
    }

    public synchronized void writeToFile(String threadName, long elapsedTime) throws IOException {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(output, true));
            out.println(threadName + " Elapsed Time: " + elapsedTime + " milliseconds" + "\n");
            out.println("");
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
    }

    public static OutputWriter getInstance() {
        return inst;
    }
}