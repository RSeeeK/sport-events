package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Jacksonized
public class EventWithUserParticipationModel extends EventModel {
    @ApiModelProperty(value = "Тип участия: FAN или SPORTSMAN или NONE")
    private UserEventModel.ParticipationType participationType;
    @ApiModelProperty(value = "Признак подтверждения участия в мероприятии")
    private Boolean approved;

    public EventWithUserParticipationModel(UUID id, String name, LocalDateTime eventDate, Boolean registrationOver, Integer limit, String description, String address, String imageUrl, UserEventModel.ParticipationType participationType, Boolean approved) {
        super(id, name, eventDate, registrationOver, limit, description, address, imageUrl);
        this.participationType = participationType;
        this.approved = approved;
    }
}
