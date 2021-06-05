package ru.devhack.motomoto.sportevents.controller;

import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devhack.motomoto.sportevents.model.ActivityModel;
import ru.devhack.motomoto.sportevents.model.UserActivity;
import ru.devhack.motomoto.sportevents.service.UserActivityService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/activity")
@Api(tags = {"activity"})
public class UserActivityController {
    private final UserActivityService userActivityService;

    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить все доступные активности")
    public @ResponseBody
    @NotNull
    ResponseEntity<List<ActivityModel>> allActivities() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userActivityService.getAllActivity());
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить активности пользователя по id")
    public @ResponseBody
    @NotNull
    ResponseEntity<List<UserActivity>> allUserActivities(@PathVariable UUID userId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userActivityService.getByUserId(userId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Создать активность пользователя")
    public @ResponseBody
    @NotNull
    ResponseEntity<UserActivity> createUserActivity(@RequestBody UserActivity userActivity) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userActivityService.save(userActivity));
    }
}
