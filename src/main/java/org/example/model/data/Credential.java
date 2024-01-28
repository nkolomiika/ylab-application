package org.example.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс Credential представляет учётные данные пользователя, включая информацию о логине и пароле.
 */
@Data
@AllArgsConstructor
public class Credential {
    private String login;
    private String password;
}
