package ru.devhack.motomoto.sportevents.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserAuthentication {
    private String employeeCode;
    private String token;
}
