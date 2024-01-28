package org.example.managers;

import org.example.commands.abstracts.Command;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchCommandException;
import org.example.model.data.Role;
import org.example.utils.console.Console;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandManager {

    private HashMap<String, Command> commands;
    private final Console console = new Console();

    public CommandManager() {
        this.commands = new HashMap<>();
    }

    public void addAllCommands(Collection<Command> commands) {
        this.commands.putAll(
                commands.stream()
                        .collect(Collectors.toMap(Command::getName, s -> s))
        );
    }

    public void addCommand(Command command) {
        this.commands.put(command.getName(), command);
    }

    public Response executeCommand(Request request) {
        String command = request.getCommand();
        if (this.commands.containsKey(command))
            return commands.get(command).execute(request);
        throw new NoSuchCommandException();
    }

    public String getAllCommands(Role role) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            var commandRole = entry.getValue().getRole();
            if (commandRole.equals(role) || commandRole.equals(Role.ALL)) {
                sb.append(i).append(". ").append(entry.getValue().toString()).append("\n");
                i++;
            }
        }

        return sb.reverse().replace(0, 1, "").reverse().toString();
    }

    public String getStarterCommands() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            sb.append(i).append(". ").append(entry.getValue().toString()).append("\n");
            i++;

        }

        return sb.reverse().replace(0, 1, "").reverse().toString();
    }

    public boolean containsCommand(String command) {
        return this.commands.containsKey(command);
    }

}
