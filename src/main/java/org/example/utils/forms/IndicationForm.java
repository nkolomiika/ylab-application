package org.example.utils.forms;

import org.example.exceptions.NegativeIndicationException;
import org.example.exceptions.NullIndicationException;
import org.example.utils.console.Console;
import org.example.utils.input.ConsoleInput;
import org.example.utils.input.UserInput;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

public class IndicationForm {
    
    private final static UserInput in = new ConsoleInput();
    private static final Console console = new Console();

    public static long askIndication(String indication) {
        String indStr;
        long ind;
        while (true) {
            try {

                console.println("Укажите значение счетчика : " + indication.toLowerCase());
                indStr = in.nextLine().trim();
                ind = Integer.parseInt(indStr);
                if (ind < 0) throw new NegativeIndicationException();
                break;

            } catch (NumberFormatException exception) {
                console.printError("Значение счетчика должно быть числом");
            } catch (NoSuchElementException | IllegalArgumentException exception) {
                console.printError("Необходимо указать значение счетчика");
            } catch (NegativeIndicationException exception) {
                console.printError("Значения счетчиков должны быть больше нуля");
            }
        }

        return ind;
    }

    public static LocalDate askDate() {
        String date;
        while (true) {
            try {
                console.println("Введите дату для просмотра показаний счетчиков");
                date = in.nextLine().trim();
                return LocalDate.parse(date);

            } catch (DateTimeParseException exception) {
                console.printError("Неправильный формат даты");
            }
        }
    }

    public static String askIndicationName() {
        String name;
        while (true) {
            try {
                console.println("Укажите название счетчика:");
                name = in.nextLine().trim();
                if (name.isEmpty() || name.isBlank()) throw new NullIndicationException();
                break;

            } catch (NumberFormatException exception) {
                console.printError("Значение счетчика должно быть числом");
            } catch (NoSuchElementException
                     | IllegalArgumentException
                     | NullIndicationException exception) {
                console.printError("Необходимо указать название счетчика");
            }
        }

        return name;
    }

}
