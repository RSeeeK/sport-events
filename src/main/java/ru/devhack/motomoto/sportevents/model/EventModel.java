package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class EventModel {
    @ApiModelProperty(value = "ID мероприятия")
    private UUID id;
    @ApiModelProperty(value = "Краткое наименование мероприятия")
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty(value = "Дата/Время мероприятия")
    private LocalDateTime eventDate;
    @ApiModelProperty(value = "Признак завершения регистрации")
    private Boolean registrationOver;
    @ApiModelProperty(value = "Лимит участников")
    private Integer limit;
    @ApiModelProperty(value = "Описание")
    private String description;
    @ApiModelProperty(value = "Место (адрес)")
    private String address;
    @ApiModelProperty(value = "URL изображения")
    private String imageUrl;
}
