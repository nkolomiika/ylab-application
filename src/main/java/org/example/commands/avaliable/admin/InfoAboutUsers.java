package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.UserManager;
import org.example.model.data.Role;

public class InfoAboutUsers extends Command {

    private UserManager userManager;

    public InfoAboutUsers(UserManager userManager) {
        super(Role.ADMIN, "info_about_all_users", "получить информацию о всех пользователях");
        this.userManager = userManager;
    }


    @Override
    public Response execute(Request request) {
        return new Response(userManager.printAllUsers(), Status.OK);
    }
}
