package ru.devhack.motomoto.sportevents.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devhack.motomoto.sportevents.model.EventModel;
import ru.devhack.motomoto.sportevents.service.EventService;

import java.util.List;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/event")
@Api(tags = {"event"})
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
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
