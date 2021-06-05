package ru.devhack.motomoto.sportevents.controller;

import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devhack.motomoto.sportevents.model.EventModel;
import ru.devhack.motomoto.sportevents.model.EventWithUserModel;
import ru.devhack.motomoto.sportevents.service.EventService;
import ru.devhack.motomoto.sportevents.service.UserEventService;

import java.util.List;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/event")
@Api(tags = {"event"})
public class EventController {
    private final EventService eventService;

    private final UserEventService userEventService;

    public EventController(EventService eventService,
                           UserEventService userEventService) {
        this.eventService = eventService;
        this.userEventService = userEventService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить все мероприятия")
    public @ResponseBody
    @NotNull
    ResponseEntity<List<EventModel>> allEvents() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.getAll());
    }

    @GetMapping(value = "/withusers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить все мероприятия c пользователями")
    public @ResponseBody
    @NotNull
    ResponseEntity<List<EventWithUserModel>> allEventsWithUser() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.getAllWithUser());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Создать новое мероприятие")
    public @ResponseBody
    @NotNull
    ResponseEntity<EventModel> createEvent(@RequestBody EventModel eventModel) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.save(eventModel));
    }
}
