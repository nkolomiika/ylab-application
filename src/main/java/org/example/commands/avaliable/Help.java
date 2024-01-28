package org.example.commands.avaliable;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.CommandManager;

public class Help extends Command {

    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "вывод информации по всем командам");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) {
        return new Response(
                commandManager.getAllCommands(),
                Status.OK
        );
    }
}
