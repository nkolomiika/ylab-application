package org.example.utils.input;

import java.util.Scanner;

public class ConsoleInput implements UserInput {
    public static final Scanner userScanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        System.out.print("> ");
        return userScanner.nextLine();
    }
}
