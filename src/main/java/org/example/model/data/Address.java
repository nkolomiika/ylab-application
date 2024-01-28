    package org.example.model.data;

    import lombok.AllArgsConstructor;
    import lombok.Data;

    /**
     * Модель представляет данные об адресе пользователя с информацией о городе, улице, номере дома и квартире.
     */
    @Data
    @AllArgsConstructor
    public class Address {
        private String city;
        private String street;
        private int house;
        private int flat;
    }
