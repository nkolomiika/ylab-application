package org.example.commands.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.data.Role;

@Data
@AllArgsConstructor
public abstract class Command implements Executable{

    protected Role role;
    protected String name;
    protected String description;

    @Override
    public String toString() {
        return name + " : " + description;
    }
}
