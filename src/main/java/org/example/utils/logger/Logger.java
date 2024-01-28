package org.example.utils.logger;

public class Logger {
    private static StringBuilder logs = new StringBuilder();

    public static void addLog(String l){
        logs.append(l);
    }

    public static void getAllLogs(){
        // todo прописать вывод всех логов
    }
}
