package org.example.model.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Indication {
    private LocalDate date;
    private Long indication;

    public Indication(Long indication) {
        this.indication = indication;
        this.date = LocalDate.now();
    }

    public Indication() {
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "дата : " + date.toString() + "; показания : " + indication;
    }

    public String indicationToString(){
        return "показания : " + indication;
    }
}
