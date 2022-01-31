package edu.isu.cs2263.hw01;

/**
 * Represents a method of outputting the program results
 * @author Tyson Cox
 */
public interface OutputWriter {
    void write(String output);
    boolean shouldWriteExpression();
}
