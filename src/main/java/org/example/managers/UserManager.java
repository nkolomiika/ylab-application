package org.example.managers;

import org.example.exceptions.NoSuchUserException;
import org.example.model.User;
import org.example.model.data.Address;
import org.example.model.data.Credential;
import org.example.model.data.Role;
import org.example.model.data.SNP;
import org.example.utils.forms.UserDataForm;

import java.util.HashMap;


/**
 * Менеджер пользователей отвечает за управление пользователями, их проверку, добавление, удаление и т. д.
 */
public class UserManager {
    private HashMap<String, User> users = new HashMap<>();


    public UserManager() {
        User admin = this.createAdmin();
        this.users.put(admin.getCredential().getLogin(), admin);
    }

    /**
     * Проверяет пользователя по учетным данным.
     *
     * @param credential Учетные данные пользователя
     * @return true, если пользователь существует и учетные данные верны, в противном случае - false
     * @throws NoSuchUserException если пользователь не существует
     */
    public boolean checkUser(Credential credential) throws NoSuchUserException {
        return this.checkLogin(credential.getLogin())
                && this.checkPassword(credential);
    }

    public boolean checkLogin(String login) {
        return this.users.containsKey(login);
    }

    private boolean checkPassword(Credential creds) {
        String login = creds.getLogin(), password = creds.getPassword();
        User user = this.users.get(login);
        return user.getCredential().getPassword().equals(password);
    }

    public User getUserByLogin(String login) throws NoSuchUserException {
        var user = this.users.get(login);
        if (user == null) throw new NoSuchUserException();
        return user;
    }

    public void addUser(User user) {
        this.users.put(user.getCredential().getLogin(), user);
    }

    public void removeUser(User user) {
        this.users.remove(user.getCredential().getLogin());
    }

    /**
     * Печатает информацию о пользователе.
     *
     * @param user Пользователь, информацию о котором требуется напечатать
     * @return Информация о пользователе в виде строки
     */
    public String printUser(User user) {
        return this.users.get(user.getCredential().getLogin()).toBeautyString();
    }

    /**
     * Печатает информацию о всех пользователях.
     *
     * @return Информация о всех пользователях в виде строки
     */
    public String printAllUsers() {
        StringBuilder sb = new StringBuilder();
        this.users.forEach((login, user) -> sb.append(this.printUser(user)).append("\n"));
        return sb.toString();
    }

    /**
     * Проверяет, существует ли пользователь с указанными учетными данными.
     *
     * @param creds Учетные данные пользователя
     * @return Пользователь, если пользователь существует
     * @throws NoSuchUserException если пользователь не существует
     */
    public User containsUser(Credential creds) throws NoSuchUserException {
        String login = creds.getLogin();
        if (this.checkUser(creds))
            return this.getUserByLogin(login);
        throw new NoSuchUserException();
    }

    /**
     * Проверяет, существует ли пользователь с указанным логином.
     *
     * @param login Логин пользователя
     * @return Пользователь, если пользователь существует
     * @throws NoSuchUserException если пользователь не существует
     */
    public User containsUserByLogin(String login) throws NoSuchUserException {
        if (this.checkLogin(login))
            return this.getUserByLogin(login);
        throw new NoSuchUserException();
    }

    /**
     * Ищет пользователя по введенному логину.
     *
     * @return Пользователь с указанным логином
     */
    public User searchUser() {
        var login = UserDataForm.askLogin();
        return this.getUserByLogin(login);
    }

    /**
     * Создает пользователя с правами администратора.
     *
     * @return Созданный пользователь с правами администратора
     */
    private User createAdmin() {
        return new User(
                new Credential("admin@mail.ru", "admin1"),
                new SNP("Коломиец", "Никита", "Сергеевич"),
                new Address("Санкт-Петербург", "Вяземский пер.", 5, 119),
                Role.ADMIN
        );
    }

    /**
     * Повышает пользователя до роли администратора.
     *
     * @return Пользователь с повышенными правами администратора
     * @throws NoSuchUserException если пользователь не существует
     */
    public User boostUser() throws NoSuchUserException {
        String login = UserDataForm.askLogin();
        User user = this.containsUserByLogin(login);
        user.setRole(Role.ADMIN);
        return user;
    }
}
