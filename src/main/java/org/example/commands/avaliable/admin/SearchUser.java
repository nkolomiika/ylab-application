package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchUserException;
import org.example.managers.UserManager;

public class SearchUser extends Command {

    private UserManager userManager;

    public SearchUser(UserManager userManager) {
        super("search_user", "просмотр информации о конкретном пользователе");
        this.userManager = userManager;
    }

    @Override
    public Response execute(Request request)  {
        try {
            return new Response(
                    this.userManager.printUser(this.userManager.searchUser()),
                    Status.OK
            );
        } catch (NoSuchUserException exception){
            return new Response("Пользователя с таким логином не существует", Status.ERROR);
        }
    }
}
