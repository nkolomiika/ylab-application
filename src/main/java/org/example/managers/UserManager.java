package org.example.managers;

import org.example.exceptions.NoSuchUserException;
import org.example.model.User;
import org.example.model.data.Address;
import org.example.model.data.Credential;
import org.example.model.data.Role;
import org.example.model.data.SNP;
import org.example.utils.forms.UserDataForm;
import org.example.utils.input.ConsoleInput;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users = new HashMap<>();

    public UserManager() {
        User admin = this.createAdmin();
        this.users.put(admin.getCredential().getLogin(), admin);
    }

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

    public String printUser(User user) {
        return this.users.get(user.getCredential().getLogin()).toBeautyString();
    }

    public String printAllUsers() {
        StringBuilder sb = new StringBuilder();
        this.users.forEach((login, user) -> sb.append(this.printUser(user)).append("\n"));
        return sb.toString();
    }

    public User containsUser(Credential creds) throws NoSuchUserException {
        String login = creds.getLogin();
        if (this.checkUser(creds))
            return this.getUserByLogin(login);
        throw new NoSuchUserException();
    }

    public User containsUserByLogin(String login) throws NoSuchUserException {
        if (this.checkLogin(login))
            return this.getUserByLogin(login);
        throw new NoSuchUserException();
    }

    public User searchUser() {
        var login = UserDataForm.askLogin();
        return this.getUserByLogin(login);
    }

    private User createAdmin() {
        return new User(
                new Credential("admin@mail.ru", "admin1"),
                new SNP("Коломиец", "Никита", "Сергеевич"),
                new Address("Санкт-Петербург", "Вяземский пер.", 5, 119),
                Role.ADMIN
        );
    }

    public User boostUser() throws NoSuchUserException {
        String login = UserDataForm.askLogin();
        User user = this.containsUserByLogin(login);
        user.setRole(Role.ADMIN);
        return user;
    }
}
