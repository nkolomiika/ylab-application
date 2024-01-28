package org.example.commands.avaliable.user;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.EmptyIndicationException;
import org.example.exceptions.NoSuchDateException;
import org.example.managers.IndicationsManager;
import org.example.utils.console.Console;

public class CheckIndications extends Command {

    private IndicationsManager indicationsManager;
    private final Console console = new Console();

    public CheckIndications(IndicationsManager indicationsManager) {
        super("check_indications", "просмотр значений счетчиков за определенный месяц");
        this.indicationsManager = indicationsManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            StringBuilder sb = this.indicationsManager.getAllDatesOfIndicationOfUser(request.getUser());
            console.println(sb.toString());
            return new Response(
                    this.indicationsManager.getIndicationByDate(request.getUser()),
                    Status.OK
            );
        } catch (EmptyIndicationException exception) {
            return new Response("Значения счетчиков пусты", Status.ERROR);
        } catch (NoSuchDateException exception) {
            return new Response("Такой даты нет среди показаний счетчиков", Status.ERROR);
        }
    }
}
