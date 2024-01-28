package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.model.data.Address;
import org.example.model.data.Credential;
import org.example.model.data.Role;
import org.example.model.data.SNP;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private Credential credential;
    private SNP snp;
    private Address address;
    private Role role;

    public User(Credential credential, Role role) {
        this.credential = credential;
        this.role = role;
    }

    @Override
    public String toString() {
        return credential.getLogin() + " -> \n" +
                "   Фамилия : " + snp.getSurname() +
                "\n   Имя : " + snp.getName() +
                "\n   Отчество : " + snp.getSurname() +
                "\n   Город : " + address.getCity() +
                "\n   Улица : " + address.getStreet() +
                "\n   Дом : " + address.getHouse() +
                "\n   Квартира : " + address.getFlat() +
                "\n   Роль : " + role;
    }
}
