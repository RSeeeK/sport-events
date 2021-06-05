package ru.devhack.motomoto.sportevents.controller;

import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devhack.motomoto.sportevents.config.security.JwtTokenProvider;
import ru.devhack.motomoto.sportevents.db.entity.User;
import ru.devhack.motomoto.sportevents.model.EventModel;
import ru.devhack.motomoto.sportevents.model.EventWithUserModel;
import ru.devhack.motomoto.sportevents.model.EventWithUserParticipationModel;
import ru.devhack.motomoto.sportevents.service.EventService;
import ru.devhack.motomoto.sportevents.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/event")
@Api(tags = {"event"})
public class EventController {
    private final EventService eventService;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    public EventController(EventService eventService,
                           JwtTokenProvider jwtTokenProvider,
                           UserService userService) {
        this.eventService = eventService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
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

    @GetMapping(value = "/by-current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить все мероприятия c параметрами участия текущего пользователя")
    public @ResponseBody
    @NotNull
    ResponseEntity<List<EventWithUserParticipationModel>> allEventsWithUserParticipation(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        String employeeCode = jwtTokenProvider.getUsername(token);
        Optional<User> userOptional = userService.findUserByEmployeeCode(employeeCode);
        return userOptional.map(user -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.getAllWithUserParticipation(user.getId())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());

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
