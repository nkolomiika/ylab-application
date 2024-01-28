package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.IndicationsManager;

public class AddIndication extends Command {

    private IndicationsManager indicationsManager;

    public AddIndication(IndicationsManager indicationsManager) {
        super("add_indication", "добавить новый счетчик");
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
