package ru.devhack.motomoto.sportevents.controller;

import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devhack.motomoto.sportevents.model.UserEventModel;
import ru.devhack.motomoto.sportevents.service.UserEventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/user-event")
@Api(tags = {"user-event"})
public class UserEventController {
    private final UserEventService userEventService;

    public UserEventController(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить все мероприятия пользователя по ID")
    public @ResponseBody
    @NotNull
    ResponseEntity<List<UserEventModel>> allEventByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userEventService.getAllByUserId(userId));
    }
}
