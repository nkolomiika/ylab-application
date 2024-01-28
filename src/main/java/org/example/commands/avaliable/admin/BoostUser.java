package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchUserException;
import org.example.managers.UserManager;
import org.example.model.data.Role;

public class BoostUser extends Command {

    private UserManager userManager;

    public BoostUser(UserManager userManager) {
        super(Role.ADMIN, "boost", "повышает права пользователя до админа");
        this.userManager = userManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            return new Response("Пользователь "
                    + this.userManager.boostUser().getCredential().getLogin()
                    + " получил права админа", Status.OK);
        } catch (NoSuchUserException exception) {
            return new Response("Пользователя с таким логином не существует", Status.ERROR);
        }
    }
}
