package ru.devhack.motomoto.sportevents.service;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.db.entity.UserEvent;
import ru.devhack.motomoto.sportevents.db.repository.UserEventRepository;
import ru.devhack.motomoto.sportevents.model.UserEventModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserEventService {
    private final UserEventRepository userEventRepository;

    public UserEventService(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
    }

    public List<UserEventModel> getAllByUserId(@NotNull UUID userId) {
        return userEventRepository.findAllByUserId(userId).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public List<UserEventModel> getAllByEventId(@NotNull UUID eventId) {
        return userEventRepository.findAllByEventId(eventId).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public UserEventModel save(@NotNull UserEventModel userEventModel) {
        userEventRepository.save(convertToUserEvent(userEventModel));
        return userEventModel;
    }

    public Optional<UserEventModel> getByUserEventId(@NotNull UUID userId, @NotNull UUID eventId) {
        return userEventRepository.findAllByUserAndEventId(userId, eventId).stream()
                .findFirst()
                .map(this::convertToModel);
    }

    public UserEventModel approveUserParticipation(UserEventModel userEventModel) {
        userEventModel.setApproved(true);
        save(userEventModel);
        return userEventModel;
    }

    public UserEvent convertToUserEvent(UserEventModel userEventModel) {
        return UserEvent.builder()
                .userEventPK(UserEvent.UserEventPK.builder()
                        .eventId(userEventModel.getEventId())
                        .userId(userEventModel.getUserId())
                        .build())
                .participationType(userEventModel.getParticipationType() != null ? userEventModel.getParticipationType().name() : null)
                .approved(userEventModel.getApproved())
                .build();
    }

    public UserEventModel convertToModel(UserEvent userEvent) {
        return UserEventModel.builder()
                .eventId(userEvent.getUserEventPK() != null ? userEvent.getUserEventPK().getEventId() : null)
                .userId(userEvent.getUserEventPK() != null ? userEvent.getUserEventPK().getUserId() : null)
                .participationType(userEvent.getParticipationType() != null && !userEvent.getParticipationType().isEmpty()
                        ? UserEventModel.ParticipationType.valueOf(userEvent.getParticipationType())
                        : null)
                .approved(userEvent.getApproved())
                .build();
    }

}
