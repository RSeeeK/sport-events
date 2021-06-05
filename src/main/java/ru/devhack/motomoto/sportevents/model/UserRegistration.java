package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class UserRegistration {
    @ApiModelProperty(value = "Логин (табельный номер)")
    private String employeeCode;
    @ApiModelProperty(value = "Пароль")
    private String password;
    @ApiModelProperty(value = "Имя")
    private String firstName;
    @ApiModelProperty(value = "Отчество")
    private String middleName;
    @ApiModelProperty(value = "Фамилия")
    private String lastName;
    @ApiModelProperty(value = "URL изображения")
    private String imageUrl;
    @ApiModelProperty(value = "Роль пользователя")
    private UserModel.UserRole role;
}
