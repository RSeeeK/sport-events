package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Jacksonized
@SuperBuilder
public class EventWithUserModel extends EventModel {
    @ApiModelProperty(value = "Список участников мероприятия")
    private List<UserEventModel> userRegistered;
}
