package ru.devhack.motomoto.sportevents.service;

import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.db.entity.Event;
import ru.devhack.motomoto.sportevents.db.repository.EventRepository;
import ru.devhack.motomoto.sportevents.model.EventModel;
import ru.devhack.motomoto.sportevents.model.EventWithUserModel;

import java.util.List;
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
        return new EventWithUserModel(event.getId(),
                event.getName(),
                event.getEventDate(),
                event.getRegistrationOver(),
                event.getEventLimit(),
                event.getDescription(),
                event.getAddress(),
                event.getImageUrl(),
                userEventService.getAllByEventId(event.getId()));
    }
}
