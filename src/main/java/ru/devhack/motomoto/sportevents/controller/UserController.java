package ru.devhack.motomoto.sportevents.controller;

import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devhack.motomoto.sportevents.model.UserModel;
import ru.devhack.motomoto.sportevents.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/user")
@Api(tags = {"user"})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить всех пользователей")
    public @ResponseBody
    @NotNull
    ResponseEntity<List<UserModel>> allUsers() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getAll());
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить пользователя по ID")
    public @ResponseBody
    @NotNull
    ResponseEntity<UserModel> getUserById(@PathVariable UUID userId) {
        Optional<UserModel> userModelOptional = userService.findUserById(userId);
        return userModelOptional.map(userModel -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userModel))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
