package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.model.data.Role;
import org.example.utils.logger.Logger;

public class LoggerCommand extends Command {

    public LoggerCommand() {
        super(Role.ADMIN, "logs", "просмотр аудита системы");
    }

    @Override
    public Response execute(Request request) {
        return new Response(
                Logger.getAllLogs(),
                Status.OK
        );
    }
}
