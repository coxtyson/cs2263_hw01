package edu.isu.cs2263.hw01;

/**
 * Writes text to the console
 * @author Tyson Cox
 */
public class ConsoleWriter implements OutputWriter {
    /**
     * Write some text to the console
     * @param output The text to output
     */
    @Override
    public void write(String output) {
        System.out.println(output);
    }

    /**
     * @return Whether this write should also write the expression
     */
    @Override
    public boolean shouldWriteExpression() {
        return false;
    }
}
