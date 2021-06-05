package ru.devhack.motomoto.sportevents.service;

import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.model.UserEventModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserEventService {
    private final List<UserEventModel> data = new ArrayList<>(); {
        data.add(UserEventModel.builder()
                .userId(UUID.fromString("f4b40b03-79e2-41d9-8223-952cffea90c7"))
                .eventId(UUID.fromString("2268c91e-a158-467b-8a66-9779a1a9eefd"))
                .build());
        data.add(UserEventModel.builder()
                .userId(UUID.fromString("fc75510b-1d44-40cd-8b2a-020395287612"))
                .eventId(UUID.fromString("f5b45eaa-5cab-4e82-8a7a-bb62dd0278c1"))
                .build());
        data.add(UserEventModel.builder()
                .userId(UUID.fromString("fc75510b-1d44-40cd-8b2a-020395287612"))
                .eventId(UUID.fromString("62b5f20d-5cd8-46ec-806a-b8ad41d99c80"))
                .build());
    }

    public List<UserEventModel> getAllByUserId(UUID id) {
        return data.stream()
                .filter(userEventModel -> userEventModel.getUserId().equals(id))
                .collect(Collectors.toList());
    }
}
