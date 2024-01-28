package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.User;

@Data
@AllArgsConstructor
public class Request {
    private User user;
    private String command;

    public Request(String command) {
        this.command = command;
    }
}
