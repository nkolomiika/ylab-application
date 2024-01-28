package org.example.commands.avaliable.user;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.IndicationsManager;
import org.example.model.data.Role;

public class AddIndications extends Command {

    private IndicationsManager indicationsManager;

    public AddIndications(IndicationsManager indicationsManager) {
        super(Role.USER, "add_indications", "добавление показаний счетчиков");
        this.indicationsManager = indicationsManager;
    }

    @Override
    public Response execute(Request request) {
        if (this.indicationsManager.addIndicationsByUser(request.getUser()))
            return new Response("Данные успешно добавлены", Status.OK);

        return new Response("Вы уже добавляли данные в этом месяце", Status.ERROR);
    }
}
