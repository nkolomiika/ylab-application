package org.example.controllers;

import org.example.model.User;
import org.example.utils.input.UserInput;

@FunctionalInterface
public interface Controller {
    User run(UserInput in);
}
