package ru.devhack.motomoto.sportevents.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.devhack.motomoto.sportevents.model.AuthenticationRequestModel;
import ru.devhack.motomoto.sportevents.model.UserAuthentication;
import ru.devhack.motomoto.sportevents.model.UserModel;
import ru.devhack.motomoto.sportevents.service.UserService;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/auth")
@Api(tags = {"auth"})
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthentication> login(@RequestBody AuthenticationRequestModel data) {
        Optional<UserModel> userModelOptional = userService.getAll().stream()
                .filter(userModel -> userModel.getEmployeeCode().equals(data.getEmployeeCode()))
                .findFirst();
        return userModelOptional.map(userModel ->
                ok(UserAuthentication.builder()
                        .id(userModel.getId())
                        .employeeCode(userModel.getEmployeeCode())
                        .token("Bearer YWxhZGRpbjpvcGVuc2VzYW1lKfdjKlrekKJKL")
                        .build()))
                .orElseGet(() -> ok(UserAuthentication.builder()
                        .employeeCode(data.getEmployeeCode())
                        .token("Bearer YWxhZGRpbjpvcGVuc2VzYW1lKfdjKlrekKJKL")
                        .build()));

    }
}
