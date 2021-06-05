package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserAuthentication {
    @ApiModelProperty(value = "ID пользователя")
    private UUID id;
    @ApiModelProperty(value = "Табельный номер сотрудника")
    private String employeeCode;
    @ApiModelProperty(value = "Токен авторизации")
    private String token;
    @ApiModelProperty(value = "Результат авторизации")
    private Boolean result;
    @ApiModelProperty(value = "Текст ошибки")
    private String errorMessage;
}
