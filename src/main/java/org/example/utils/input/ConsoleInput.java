package org.example.utils.input;

import java.util.Scanner;

/**
 * Класс ConsoleInput представляет собой реализацию интерфейса UserInput,
 * позволяющую получать ввод с консоли с помощью Scanner.
 */
public class ConsoleInput implements UserInput {
    /**
     * Объект Scanner для считывания пользовательского ввода.
     */
    public static final Scanner userScanner = new Scanner(System.in);

    /**
     * Считывает строку текста, введенную пользователем в консоли.
     * @return введенная пользователем строка текста
     */
    @Override
    public String nextLine() {
        System.out.print("> "); // Предложение пользователю ввести данные
        return userScanner.nextLine();
    }
}
