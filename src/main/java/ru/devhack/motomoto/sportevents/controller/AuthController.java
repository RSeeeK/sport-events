package ru.devhack.motomoto.sportevents.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.devhack.motomoto.sportevents.model.AuthenticationRequestModel;
import ru.devhack.motomoto.sportevents.model.UserAuthentication;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/auth")
@Api(tags = {"auth"})
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<UserAuthentication> login(@RequestBody AuthenticationRequestModel data) {
        return ok(UserAuthentication.builder()
                .employeeCode(data.getEmployeeCode())
                .token("Bearer YWxhZGRpbjpvcGVuc2VzYW1lKfdjKlrekKJKL")
                .build());
    }
}
