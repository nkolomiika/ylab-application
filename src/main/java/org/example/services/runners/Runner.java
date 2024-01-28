package org.example.services.runners;

import org.example.exceptions.ExitObligedException;
import org.example.model.User;
import org.example.model.data.Role;
import org.example.utils.input.UserInput;

@FunctionalInterface
public interface Runner {
    void run(UserInput in, User user, Role role)  throws ExitObligedException;
}
