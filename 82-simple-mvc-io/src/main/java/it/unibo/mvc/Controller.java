package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File currentFile;

    /**
     * Controller Constructor 0 arguments.
     */
    public Controller() {
        this.currentFile = new File(System.getProperty("user.home")
        + System.getProperty("file.separator")
        + "output.txt");
    }

    /**
     * Setter Method for the currentFile Field.
     * @param testFile
     */
    public final void setCurrentFile(final File testFile) {
        this.currentFile = testFile;
    }

    /**
     * Getter Method to get currentFile Field.
     * @return a File.
     */
    public final File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * Method usable to get the PATH of the currentFile Field. 
     * @return a PATH.
     */
    public String pathOfFIle() {
        return this.currentFile.getPath();
    }

    /**
     * Method usable to print a string into the currentFile Field.
     * @param toPrint
     * @throws IOException
     */
    public void printStringToFile(final String toPrint) throws IOException {
        try (PrintStream ps = new PrintStream(this.getCurrentFile(), StandardCharsets.UTF_8)) {
            ps.print(toPrint);
        }
    }
}
