package edu.isu.cs2263.hw01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes text output to a file
 * @author Tyson Cox
 */
public class FileOutputWriter implements OutputWriter {
    FileWriter writer;

    FileOutputWriter(String path) throws IOException {
        File file = new File(path);

        if (!file.exists()) {
            file.createNewFile();
        }

        writer = new FileWriter(path);
    }

    /**
     * Write some text
     * @param output Text to write
     */
    @Override
    public void write(String output) {
        try {
            writer.write(output + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Whether this write should also write the expression
     */
    @Override
    public boolean shouldWriteExpression() {
        return true;
    }
}
