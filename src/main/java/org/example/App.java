package org.example;

import org.example.controllers.ApplicationController;
import org.example.controllers.Controller;
import org.example.managers.IndicationsManager;
import org.example.managers.UserManager;
import org.example.model.User;
import org.example.model.data.Credential;
import org.example.model.data.Role;
import org.example.services.AuthService;
import org.example.services.RuntimeService;
import org.example.services.runners.AdminModeRunner;
import org.example.services.runners.UserModeRunner;
import org.example.utils.input.ConsoleInput;
import org.example.utils.input.UserInput;

import java.util.LinkedList;

public class App {

    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        AuthService authService = new AuthService(userManager);
        IndicationsManager indicationsManager = new IndicationsManager();
        RuntimeService runtimeService = new RuntimeService(
                new AdminModeRunner(userManager, indicationsManager),
                new UserModeRunner(indicationsManager)
        );
        Controller applicationController = new ApplicationController(authService, runtimeService);
        UserInput in = new ConsoleInput();

       /* String GREETINGS = "Добро пожаловать, {name}!";
        String v = "ghghg";
        System.out.printf("").format(GREETINGS, v);*/

        /*IndicationsManager i = new IndicationsManager();
        System.out.println(i.getUserIndications(new User(new Credential("1", "2"), Role.USER)).getClass());
        System.out.println(i.getIndicationByName(new User(new Credential("1", "2"), Role.USER), "a").getClass());*/

        applicationController.run(in);
    }
}
