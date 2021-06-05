package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class UserEventModel {
    @ApiModelProperty(value = "ID мероприятия")
    private UUID eventId;
    @ApiModelProperty(value = "ID пользователя")
    private UUID userId;
    @ApiModelProperty(value = "Тип участия: FAN или SPORTSMAN")
    private ParticipationType participationType;

    enum ParticipationType {
        FAN("Болельщик"),
        SPORTSMAN("Спортсмен");

        private final String description;

        ParticipationType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
