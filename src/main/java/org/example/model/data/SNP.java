package org.example.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Представляет данные об имени человека, включая фамилию, имя и отчество.
 */
@Data
@AllArgsConstructor
public class SNP {
    private String surname;
    private String name;
    private String patronymic;
}
