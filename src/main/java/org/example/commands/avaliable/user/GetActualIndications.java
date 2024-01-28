package org.example.commands.avaliable.user;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.EmptyIndicationException;
import org.example.managers.IndicationsManager;

public class GetActualIndications extends Command {

    private IndicationsManager indicationsManager;

    public GetActualIndications(IndicationsManager indicationsManager) {
        super("get_actual_indications", "просмотр актуальных показаний счетчиков");
        this.indicationsManager = indicationsManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            return new Response(
                    indicationsManager.lastIndicationToString(request.getUser()),
                    Status.OK
            );
        } catch (EmptyIndicationException exception){
            return new Response("Показания счетчиков пусты", Status.ERROR);
        }

    }
}
