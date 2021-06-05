package ru.devhack.motomoto.sportevents.service;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.db.entity.Event;
import ru.devhack.motomoto.sportevents.db.repository.EventRepository;
import ru.devhack.motomoto.sportevents.model.EventModel;
import ru.devhack.motomoto.sportevents.model.EventWithUserModel;
import ru.devhack.motomoto.sportevents.model.EventWithUserParticipationModel;
import ru.devhack.motomoto.sportevents.model.UserEventModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    private final UserEventService userEventService;

    public EventService(EventRepository eventRepository, UserEventService userEventService) {
        this.eventRepository = eventRepository;
        this.userEventService = userEventService;
    }

    public List<EventModel> getAll() {
        return eventRepository.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public List<EventWithUserModel> getAllWithUser() {
        return eventRepository.findAll().stream()
                .map(this::convertToModelWithUser)
                .collect(Collectors.toList());
    }

    public List<EventWithUserParticipationModel> getAllWithUserParticipation(UUID userId) {
        List<UserEventModel> userEventModels = userEventService.getAllByUserId(userId);
        return eventRepository.findAll().stream()
                .map(event -> {
                    UserEventModel userEventModel = userEventModels.stream()
                            .filter(model -> model.getEventId().equals(event.getId()))
                            .findFirst()
                            .orElse(null);
                    return convertToModelWithUserParticipation(event, userEventModel);
                })
                .collect(Collectors.toList());
    }

    public EventModel save(EventModel eventModel) {
        Event event = save(convertToEvent(eventModel));
        eventModel.setId(event.getId());
        return eventModel;
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    private Event convertToEvent(EventModel eventModel) {
        return Event.builder()
                .id(eventModel.getId())
                .eventDate(eventModel.getEventDate())
                .registrationOver(eventModel.getRegistrationOver())
                .imageUrl(eventModel.getImageUrl())
                .eventLimit(eventModel.getLimit())
                .description(eventModel.getDescription())
                .address(eventModel.getAddress())
                .name(eventModel.getName())
                .build();
    }

    public EventModel convertToModel(Event event) {
        return EventModel.builder()
                .id(event.getId())
                .eventDate(event.getEventDate())
                .registrationOver(event.getRegistrationOver())
                .imageUrl(event.getImageUrl())
                .limit(event.getEventLimit())
                .description(event.getDescription())
                .address(event.getAddress())
                .name(event.getName())
                .build();
    }

    public EventWithUserModel convertToModelWithUser(Event event) {
        return EventWithUserModel.builder()
                .id(event.getId())
                .name(event.getName())
                .eventDate(event.getEventDate())
                .registrationOver(event.getRegistrationOver())
                .limit(event.getEventLimit())
                .description(event.getDescription())
                .address(event.getAddress())
                .imageUrl(event.getImageUrl())
                .userRegistered(userEventService.getAllByEventId(event.getId()))
                .build();
    }

    public EventWithUserParticipationModel convertToModelWithUserParticipation(Event event, @Nullable UserEventModel userEventModel) {
        UserEventModel.ParticipationType participationType = userEventModel == null
                ? UserEventModel.ParticipationType.NONE
                : userEventModel.getParticipationType();

        Boolean approved = userEventModel != null && userEventModel.getApproved();

        return EventWithUserParticipationModel.builder()
                .id(event.getId())
                .name(event.getName())
                .eventDate(event.getEventDate())
                .registrationOver(event.getRegistrationOver())
                .limit(event.getEventLimit())
                .description(event.getDescription())
                .address(event.getAddress())
                .imageUrl(event.getImageUrl())
                .participationType(participationType)
                .approved(approved)
                .build();
    }
}
