package org.example.commands.abstracts;

import org.example.dto.Request;
import org.example.dto.Response;

@FunctionalInterface
public interface Executable {
    Response execute(Request request);
}
