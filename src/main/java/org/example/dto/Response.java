package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.commands.abstracts.Status;
import org.example.model.User;

@Data
@AllArgsConstructor
public class Response {

    private User user;
    private String message;
    private Status status;

    public Response(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public Response(Status status, User user) {
        this.status = status;
        this.user = user;
    }
}
