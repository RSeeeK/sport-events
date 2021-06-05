package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class EventWithUserParticipationModel extends EventModel {
    @ApiModelProperty(value = "Тип участия: FAN или SPORTSMAN или NONE")
    private UserEventModel.ParticipationType participationType;
    @ApiModelProperty(value = "Признак подтверждения участия в мероприятии")
    private Boolean approved;
}
