package org.example.managers;

import org.example.exceptions.EmptyIndicationException;
import org.example.exceptions.NoSuchDateException;
import org.example.model.User;
import org.example.model.data.Indication;
import org.example.utils.forms.IndicationForm;
import org.example.utils.input.ConsoleInput;
import org.example.utils.input.UserInput;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IndicationsManager {
    private final UserInput in = new ConsoleInput();

    private List<String> types = new LinkedList<>() {{
        add("Горячая вода");
        add("Холодная вода");
        add("Счетчик отопления");
    }};
    private HashMap<User, HashMap<String, LinkedList<Indication>>> indications = new HashMap<>();

    public HashMap<String, LinkedList<Indication>> getUserIndications(User user) {
        var indications = this.indications.get(user);
        if (!this.indications.containsKey(user))
            indications = this.createIndicationForUser(user);

        return indications;
    }

    private boolean isEmptyIndications(HashMap<String, LinkedList<Indication>> indication) {
        boolean flag = true;
        for (String type : types) {
            if (!indication.get(type).isEmpty()) flag = false;
        }
        return flag;
    }

    public boolean addIndicationsByUser(User user) {
        var userIndication = this.getUserIndications(user);
        boolean isFirstAdd = this.isEmptyIndications(userIndication);

        for (Map.Entry<String, LinkedList<Indication>> entry : userIndication.entrySet()) {
            var ind = new Indication();
            if (!isFirstAdd) {
                if (!checkDate(user, ind))
                    return false;
            }
            ind.setIndication(IndicationForm.askIndication(in, entry.getKey()));
            entry.getValue().addLast(ind);
        }
        return true;
    }

    /*public HashMap<String, LocalDate> getIndicationByDate(LocalDate date, User user) {
        var map = new HashMap<String, LocalDate>();
        var userIndication = this.getUserIndications(user);
        for (Map.Entry<String, LinkedList<Indication>> entry : userIndication.entrySet()) {
            var includeList = entry.getValue();
            for (Indication i : includeList) {
                if (i.getDate().getMonthValue() == date.getMonthValue()
                        && i.getDate().getYear() == date.getYear())
                    map.put(entry.getKey(), i.getDate());
            }
        }
        return map;
    }*/

    public StringBuilder getAllDatesOfIndicationOfUser(User user) throws EmptyIndicationException {
        var userIndication = this.getUserIndications(user);
        if (this.isEmptyIndications(userIndication)) throw new EmptyIndicationException();
        var firstEntry = userIndication.entrySet().iterator().next();

        int size = firstEntry.getValue().size();
        var list = firstEntry.getValue();

        StringBuilder result = new StringBuilder().append("Список всех дат, когда были записаны показания счетчиков:\n");

        for (int i = 0; i < size; i++) {
            result.append(list.get(i).getDate());
            if (i != size - 1)
                result.append("\n");
        }

        return result;
    }

    private boolean checkDate(User user, Indication indication) {
        var lastMonth = this.getLastDateIndicationOfUser(user);
        var currentMonth = indication.getDate().getMonthValue();
        return lastMonth.plusMonths(1).getMonthValue() <= currentMonth;
    }

    private LocalDate getLastDateIndicationOfUser(User user) {
        var userIndication = this.getUserIndications(user);
        var firstEntry = userIndication.entrySet().iterator().next();
        return firstEntry.getValue().getLast().getDate();
    }

    private HashMap<String, LinkedList<Indication>> createIndicationForUser(User user) {
        HashMap<String, LinkedList<Indication>> userIndications = new HashMap<>();

        for (String type : this.types)
            userIndications.put(type, new LinkedList<>());

        this.indications.put(user, userIndications);
        return userIndications;
    }

    public String getHistoryOfUser(User user) throws EmptyIndicationException {
        var dates = this.getAllDatesOfIndicationOfUser(user).toString().split("\\n");
        var sb = new StringBuilder();
        for (int i = 1; i < dates.length; i++)
            sb.append(this.getIndicationByDate(user, LocalDate.parse(dates[i]))).append("\n");
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }

    public String lastIndicationToString(User user) throws EmptyIndicationException {
        StringBuilder result = new StringBuilder();
        String tab = "    ";
        var userIndications = this.getUserIndications(user);
        if (this.isEmptyIndications(userIndications)) throw new EmptyIndicationException();
        for (Map.Entry<String, LinkedList<Indication>> entry : userIndications.entrySet()) {
            var list = entry.getValue();
            if (result.isEmpty()) result.append(list.getLast().getDate().toString());
            result.append("\n").append(tab)
                    .append(entry.getKey()).append(" -> ")
                    .append(list.getLast().indicationToString().toString()).append(";");
        }
        return result.toString();
    }

    /**
     * @param user авторизированный пользователь
     * @return отформатированную строку с показаниями за конкретный месяц
     */
    public String getIndicationByDate(User user) throws EmptyIndicationException, NoSuchDateException {
        LocalDate localDate = IndicationForm.askDate(new ConsoleInput());
        StringBuilder result = new StringBuilder();
        String tab = "    ";
        var userIndications = this.getUserIndications(user);
        if (this.isEmptyIndications(userIndications)) throw new EmptyIndicationException();

        for (Map.Entry<String, LinkedList<Indication>> entry : userIndications.entrySet()) {
            var list = entry.getValue();
            if (result.isEmpty()) result.append(localDate.toString());
            for (Indication i : list) {
                if (i.getDate().equals(localDate))
                    result.append("\n").append(tab)
                            .append(entry.getKey())
                            .append(" -> ").append(i.indicationToString()).append(";");
            }
        }
        if (result.isEmpty()) throw new NoSuchDateException();
        return result.toString();
    }

    private String getIndicationByDate(User user, LocalDate localDate) throws EmptyIndicationException, NoSuchDateException {
        StringBuilder result = new StringBuilder();
        String tab = "    ";
        var userIndications = this.getUserIndications(user);
        if (this.isEmptyIndications(userIndications)) throw new EmptyIndicationException();

        for (Map.Entry<String, LinkedList<Indication>> entry : userIndications.entrySet()) {
            var list = entry.getValue();
            if (result.isEmpty()) result.append(localDate.toString());
            for (Indication i : list) {
                if (i.getDate().equals(localDate))
                    result.append("\n").append(tab)
                            .append(entry.getKey())
                            .append(" -> ").append(i.indicationToString()).append(";");
            }
        }
        if (result.isEmpty()) throw new NoSuchDateException();
        return result.toString();
    }

    public String addIndication(){
        var nameIndication = IndicationForm.askIndicationName(new ConsoleInput());
        this.types.add(nameIndication);
        this.indications.forEach(((user, userIndications) -> {
            userIndications.put(nameIndication, new LinkedList<>());
        } ));
        return nameIndication;
    }
}
