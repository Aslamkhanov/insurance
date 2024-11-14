package com.javacademy.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientsFullName {
    private String name;
    private String surname;
    private String patronymic;
}
