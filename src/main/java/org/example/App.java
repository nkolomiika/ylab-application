package org.example;

import org.example.controllers.ApplicationController;
import org.example.controllers.Controller;
import org.example.managers.IndicationsManager;
import org.example.managers.UserManager;
import org.example.services.AuthService;
import org.example.services.RuntimeService;
import org.example.services.runners.InteractiveModeRunner;
import org.example.utils.input.ConsoleInput;
import org.example.utils.input.UserInput;

import java.util.List;

public class App {

    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        AuthService authService = new AuthService(userManager);
        IndicationsManager indicationsManager = new IndicationsManager();
        RuntimeService runtimeService = new RuntimeService(
                new InteractiveModeRunner(indicationsManager, userManager)
        );
        Controller applicationController = new ApplicationController(authService, runtimeService);
        UserInput in = new ConsoleInput();

        applicationController.run(in);
    }
}
