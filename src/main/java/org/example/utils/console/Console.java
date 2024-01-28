package org.example.utils.console;

/**
 * Реализация интерфейса Printable, предоставляющая методы для общения с консолью.
 */
public class Console implements Printable {

    private static final String BORDER = "--------------------------------------------------";

    @Override
    public void println(String a) {
        System.out.println(a);
    }

    @Override
    public void println(String a, ConsoleColors consoleColors) {
        this.println(ConsoleColors.toColor(a, consoleColors));
    }

    @Override
    public void print(String a, ConsoleColors consoleColors) {
        this.print(ConsoleColors.toColor(a, consoleColors));
    }

    @Override
    public void print(String a) {
        System.out.print(a);
    }

    @Override
    public void printError(String a) {
        System.out.println(ConsoleColors.RED + a + ConsoleColors.RESET);
    }

    @Override
    public void printBorder(){
        System.out.println(BORDER + BORDER);
    }


}
