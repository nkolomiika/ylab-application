package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.IndicationsManager;
import org.example.model.data.Role;

public class CreateIndication extends Command {

    private IndicationsManager indicationsManager;

    public CreateIndication(IndicationsManager indicationsManager) {
        super(Role.ADMIN, "create_indication", "добавить новый счетчик");
        this.indicationsManager = indicationsManager;
    }

    @Override
    public Response execute(Request request) {
        return new Response(
                "Индикатор " + this.indicationsManager.addIndication() + " добавлен",
                Status.OK
        );
    }
}
