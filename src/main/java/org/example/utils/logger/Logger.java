package org.example.utils.logger;

import org.example.commands.abstracts.Status;
import org.example.utils.console.Console;

public class Logger {
    private static final Console console = new Console();
    private static StringBuilder logs = new StringBuilder();

    public static void addLog(String l) {
        logs.append(l).append("\n");
    }

    public static String getAllLogs() {
        return logs.reverse().replace(0,1, "").reverse().toString();
    }

    public static String createLogString(String user, String command, Status result){
        return user + " -> " + command + " : " + result;
    }

}
