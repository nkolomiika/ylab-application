package org.example.commands.avaliable.user;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.EmptyIndicationException;
import org.example.managers.IndicationsManager;

public class History extends Command {

    private IndicationsManager indicationsManager;

    public History(IndicationsManager indicationsManager) {
        super("history", "просмотр истории подачи показаний счетчиков");
        this.indicationsManager = indicationsManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            return new Response(this.indicationsManager.getHistoryOfUser(request.getUser()), Status.OK);
        } catch (EmptyIndicationException exception) {
            return new Response("История пуста", Status.ERROR);
        }
    }
}
