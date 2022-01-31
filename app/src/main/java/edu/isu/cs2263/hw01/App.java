/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.isu.cs2263.hw01;


import org.apache.commons.cli.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        Scanner scanner;
        List<OutputWriter> outputs = new ArrayList<OutputWriter>();

        options.addOption("h", "help", false, "print usage message");
        options.addOption("b", "batch", true, "batch file containing expressions to evaluate");
        options.addOption("o", "output", true, "output file");

        try {
            cmd = parser.parse(options, args);
        }
        catch (Exception ex) {
            System.out.println("Exception while parsing command line arguments: ");
            ex.printStackTrace();
            return;
        }

        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("eval [OPTIONS]", "Evaluation of simple mathematical expressions", options, "\nCopyright (C) 2022 Tyson Cox");
            return;
        }

        if (cmd.hasOption("b")) {
            String path = cmd.getOptionValue("b");
            System.out.println("Batch value: " + path);

            try {
                scanner = new Scanner(new File(path));
            }
            catch (Exception ex) {
                System.out.println("File not found: " + path);
                return;
            }
        }
        else {
            scanner = new Scanner(System.in);
        }

        if (cmd.hasOption("o")) {
            String path = cmd.getOptionValue("o");
            System.out.println("Output value: " + path);
            try {
                outputs.add(new FileOutputWriter(path));
            }
            catch (Exception ex) {
                System.out.println("Failed to open output file: " + path);
            }
        }

        outputs.add(new ConsoleWriter());

        while(scanner.hasNext()) {
            String expression = scanner.nextLine();
            String result = "-> " + evaluateExpression(expression);

            for (OutputWriter writer : outputs) {
                if (writer.shouldWriteExpression())
                    writer.write(expression);

                writer.write(result);
            }
        }
    }

    static int evaluateExpression(String expression) {
        String[] array = expression.split(" ");

        int result = Integer.parseInt(array[0]);

        for(int i = 1; i < array.length; i+=2) {
            switch (array[i]) {
                case "*" -> result *= Integer.parseInt(array[i + 1]);
                case "-" -> result -= Integer.parseInt(array[i + 1]);
                case "+" -> result += Integer.parseInt(array[i + 1]);
                case "/" -> result /= Integer.parseInt(array[i + 1]);
            }
        }

        return result;
    }
}
