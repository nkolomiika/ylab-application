package org.example.commands.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Command implements Executable{

    protected String name;
    protected String description;

    @Override
    public String toString() {
        return name + " : " + description;
    }
}
