package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.data.Address;
import org.example.model.data.Credential;
import org.example.model.data.Role;
import org.example.model.data.SNP;

/**
 * Представляет собой модель пользователя в системе.
 */
@Data
@AllArgsConstructor
public class User {

    private Credential credential;
    private SNP snp;
    private Address address;
    private Role role;
    private Role sessionRole;

    public User(Credential credential, SNP snp, Address address, Role role) {
        this.credential = credential;
        this.snp = snp;
        this.address = address;
        this.role = role;
        this.sessionRole = role;
    }

    /**
     * Создает строковое представление пользователя для отображения.
     *
     * @return Строковое представление данных пользователя
     */
    public String toBeautyString() {
        return credential.getLogin() + " -> \n" +
                "   Фамилия : " + snp.getSurname() +
                "\n   Имя : " + snp.getName() +
                "\n   Отчество : " + snp.getPatronymic() +
                "\n   Город : " + address.getCity() +
                "\n   Улица : " + address.getStreet() +
                "\n   Дом : " + address.getHouse() +
                "\n   Квартира : " + address.getFlat() +
                "\n   Роль : " + role;
    }
}
