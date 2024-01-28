package org.example.utils.console;
/**
 * Интерфейс, определяющий методы для вывода текста в консоль.
 */
public interface Printable {
    /**
     * Печатает текст с новой строкой.
     *
     * @param out Выводимый текст
     */
    void println(String out);

    /**
     * Печатает текст.
     *
     * @param out Выводимый текст
     */
    void print(String out);

    /**
     * Печатает текст для сообщения об ошибке.
     *
     * @param out Текст сообщения об ошибке
     */
    void printError(String out);

    /**
     * Печатает границу.
     */
    void printBorder();

    /**
     * Печатает текст с заданным цветом.
     *
     * @param a               Выводимый текст
     * @param consoleColors Цвет текста
     */
    default void println(String a, ConsoleColors consoleColors) {
        println(a);
    }

    /**
     * Печатает текст с заданным цветом.
     *
     * @param a               Выводимый текст
     * @param consoleColors Цвет текста
     */
    default void print(String a, ConsoleColors consoleColors) {
        print(a);
    }
}
