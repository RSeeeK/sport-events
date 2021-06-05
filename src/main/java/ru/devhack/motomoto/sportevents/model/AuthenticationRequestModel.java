package ru.devhack.motomoto.sportevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestModel {
    private String employeeCode;
    private String password;
}
