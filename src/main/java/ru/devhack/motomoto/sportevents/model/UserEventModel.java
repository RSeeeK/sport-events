package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class UserEventModel {
    @ApiModelProperty(value = "ID мероприятия")
    private UUID eventId;
    @ApiModelProperty(value = "ID пользователя")
    private UUID userId;
    @ApiModelProperty(value = "Тип участия: FAN или SPORTSMAN")
    private ParticipationType participationType;
    @ApiModelProperty(value = "Признак подтверждения участия в мероприятии")
    private Boolean approved;

    public enum ParticipationType {
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
