package ru.devhack.motomoto.sportevents.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class UserEventModel {
    private UUID eventId;
    private UUID userId;
}
