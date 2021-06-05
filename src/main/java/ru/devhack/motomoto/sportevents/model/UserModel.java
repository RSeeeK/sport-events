package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class UserModel {
    @ApiModelProperty(value = "ID пользователя")
    private UUID id;
    @ApiModelProperty(value = "Табельный номер")
    private String employeeCode;
    @ApiModelProperty(value = "Имя")
    private String firstName;
    @ApiModelProperty(value = "Отчество")
    private String middleName;
    @ApiModelProperty(value = "Фамилия")
    private String lastName;
    @ApiModelProperty(value = "URL изображения")
    private String imageUrl;
}
