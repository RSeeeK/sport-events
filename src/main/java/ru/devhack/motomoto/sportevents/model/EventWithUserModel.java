package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Jacksonized
public class EventWithUserModel extends EventModel {
    @ApiModelProperty(value = "Список участников мероприятия")
    private List<UserEventModel> userRegistered;

    public EventWithUserModel(UUID id, String name, LocalDateTime eventDate, Boolean registrationOver, Integer limit, String description, String address, String imageUrl, List<UserEventModel> userRegistered) {
        super(id, name, eventDate, registrationOver, limit, description, address, imageUrl);
        this.userRegistered = userRegistered;
    }
}
