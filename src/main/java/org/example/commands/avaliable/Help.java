package org.example.commands.avaliable;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.CommandManager;
import org.example.model.data.Role;

public class Help extends Command {

    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super(Role.ALL, "help", "вывод информации по всем командам");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) {
        if (request.getUser() == null)
            return new Response(
                    commandManager.getStarterCommands(),
                    Status.OK
            );
        return new Response(
                commandManager.getAllCommands(request.getUser().getSessionRole()),
                Status.OK
        );
    }
}
