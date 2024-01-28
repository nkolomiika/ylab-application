package org.example.utils.forms;

import org.example.exceptions.IncorrectPasswordFormatException;
import org.example.exceptions.MissPatternException;
import org.example.exceptions.PasswordMissMatchException;
import org.example.model.data.Address;
import org.example.model.data.Credential;
import org.example.model.data.SNP;
import org.example.utils.console.Console;
import org.example.utils.input.UserInput;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class UserDataForm {

    private static final Console console = new Console();
    private static final String MAIL_PATTERN = "[\\w]+(@)(gmail.com|mail.ru)";

    //----------------------------------------------------------------------------
    public static Address buildAddress(UserInput in) {
        console.printBorder();
        return new Address(
                askCity(in),
                askStreet(in),
                askHouse(in),
                askFlat(in)
        );
    }

    public static SNP buildSNP(UserInput in) {
        console.printBorder();
        return new SNP(
                askSurname(in),
                askName(in),
                askPatronymic(in)
        );
    }

    public static Credential buildCredential(UserInput in, boolean needTest) {
        return new Credential(
                askLogin(in),
                askPassword(in, needTest)
        );
    }

    //----------------------------------------------------------------------------
    private static String askCity(UserInput in) {
        String city;
        while (true) {
            try {

                console.println("Укажите название города  ");
                city = in.nextLine().trim();

                break;

            } catch (NoSuchElementException | IllegalArgumentException exception) {
                console.printError("Необходимо указать название города");
            }
        }

        return city;
    }

    private static String askStreet(UserInput in) {
        String street;
        while (true) {
            try {

                console.println("Укажите название улицы  ");
                street = in.nextLine().trim();

                break;

            } catch (NoSuchElementException | IllegalArgumentException exception) {
                console.printError("Необходимо указать название улицы");
            }
        }

        return street;
    }

    private static int askHouse(UserInput in) {
        String houseStr;
        int house;
        while (true) {
            try {

                console.println("Укажите номер дома  ");
                houseStr = in.nextLine().trim();
                house = Integer.parseInt(houseStr);

                break;

            } catch (NumberFormatException exception) {
                console.printError("Номер дома должен быть числом");
            } catch (NoSuchElementException | IllegalArgumentException exception) {
                console.printError("Необходимо указать номер дома");
            }
        }

        return house;
    }

    private static int askFlat(UserInput in) {
        String flatStr;
        int flat;
        while (true) {
            try {

                console.println("Укажите номер квартиры  ");
                flatStr = in.nextLine().trim();
                flat = Integer.parseInt(flatStr);

                break;

            } catch (NumberFormatException exception) {
                console.printError("Номер квартиры должен быть числом");
            } catch (NoSuchElementException | IllegalArgumentException exception) {
                console.printError("Необходимо указать номер квартиры");
            }
        }

        return flat;
    }

    //----------------------------------------------------------------------------


    private static String askSurname(UserInput in) {
        String surname;
        while (true) {
            try {

                console.println("Укажите вашу фамилию ");
                surname = in.nextLine().trim();

                break;

            } catch (NoSuchElementException | IllegalArgumentException exception) {
                console.printError("Необходимо указать свою фамилию");
            }
        }

        return surname;
    }

    private static String askName(UserInput in) {
        String name;
        while (true) {
            try {

                console.println("Укажите ваше имя  ");
                name = in.nextLine().trim();

                break;

            } catch (NoSuchElementException | IllegalArgumentException exception) {
                console.printError("Необходимо указать свое имя");
            }
        }

        return name;
    }

    private static String askPatronymic(UserInput in) {
        String patronymic;

        console.println("Укажите ваше отчество  ");
        patronymic = in.nextLine().trim();

        return patronymic;
    }

    //----------------------------------------------------------------------------

    public static String askLogin(UserInput in) {

        Pattern pattern = Pattern.compile(MAIL_PATTERN);
        String login;

        while (true) {
            try {
                console.println("Введите логин");
                login = in.nextLine().trim();
                if (!pattern.matcher(login).find()) throw new MissPatternException();
                break;

            } catch (NoSuchElementException
                     | IllegalArgumentException
                     | MissPatternException exception) {
                console.printError("Логин должен быть вида ivanov@gmail.com или ivanov@mail.ru");
            }
        }
        return login;

    }

    private static String askPassword(UserInput in, boolean needTest) {
        String password, passwordTest;
        Pattern pattern = Pattern.compile("[\\d\\^_]");

        while (true) {
            try {
                console.println("Введите пароль");
                password = in.nextLine().trim();
                if (!pattern.matcher(password).find()) throw new IncorrectPasswordFormatException();

                if (needTest) {
                    console.println("Повторите пароль");
                    passwordTest = in.nextLine().trim();
                    if (!password.equals(passwordTest)) throw new PasswordMissMatchException();
                }

                break;

            } catch (IllegalArgumentException
                     | NoSuchElementException
                     | IncorrectPasswordFormatException exception) {
                console.printError("Пароль должен состоять минимум из 7ми символов и содержать хотя бы одну цифру");
            } catch (PasswordMissMatchException exception) {
                console.printError("Пароли не совпадают. Попробуйте заново");
            }
        }

        return password;
    }

}
