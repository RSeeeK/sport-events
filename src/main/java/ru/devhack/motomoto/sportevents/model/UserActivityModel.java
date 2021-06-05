package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class UserActivityModel {
    @ApiModelProperty(value = "ID активности")
    private UUID id;
    @ApiModelProperty(value = "ID пользователя")
    private UUID userId;
    @ApiModelProperty(value = "Тип активности: RUNNING, WALKING, BICYCLING")
    private ActivityModel.ActivityType type;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty(value = "Дата/Время активности")
    private LocalDateTime activityDate;
    @ApiModelProperty(value = "URL изображения")
    private String imageUrl;
}
