package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String toPrint;
    private final List<String> history = new LinkedList<>();

    @Override
    public void setString(final String setNewString) {
        if (setNewString == null) {
            throw new IllegalStateException("Null values aren't acceptable");
        }
        this.toPrint = setNewString;
    }

    @Override
    public String getString() {
        return this.toPrint;
    }

    @Override
    public List<String> getHistory() {
        return new LinkedList<String>(history);
    }

    @Override
    public void printString() {
        if (this.toPrint == null) {
            throw new IllegalStateException("No string was present");
        }
        history.add(this.toPrint);
        System.out.println(this.toPrint); //NOPMD: exercise required it
    }
}
