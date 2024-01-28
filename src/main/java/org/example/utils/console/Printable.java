package org.example.utils.console;

public interface Printable {

    void println(String out);
    void print(String out);
    void printError(String out);

    void printBorder();

    default void println(String a, ConsoleColors consoleColors) {
        println(a);
    }

    default void print(String a, ConsoleColors consoleColors) {
        print(a);
    }

}
