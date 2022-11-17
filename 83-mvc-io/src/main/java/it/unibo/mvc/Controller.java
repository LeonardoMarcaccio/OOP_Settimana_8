package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    /**
     * Setter Method. 
     * @param setNewString
     */
    void setString(String setNewString);

    /**
     * Getter Method.
     * @return the actual String.
     */
    String getString();

    /**
     * Method to use to print all your previous String from a List of String.
     * @return a List of all the previous String.
     */
    List<String> getHistory();

    /**
     * Method used to print the saved String.
     */
    void printString();
}
