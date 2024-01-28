package org.example.commands.avaliable;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;

public class Exit extends Command {

    public Exit() {
        super("exit", "выход из аккаунта");
    }

    @Override
    public Response execute(Request request) {
        return new Response("До скорой встречи!", Status.EXIT);
    }
}
