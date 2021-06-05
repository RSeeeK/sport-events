package ru.devhack.motomoto.sportevents.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.devhack.motomoto.sportevents.config.security.JwtTokenProvider;
import ru.devhack.motomoto.sportevents.db.entity.User;
import ru.devhack.motomoto.sportevents.model.AuthenticationRequestModel;
import ru.devhack.motomoto.sportevents.model.UserAuthentication;
import ru.devhack.motomoto.sportevents.model.UserModel;
import ru.devhack.motomoto.sportevents.model.UserRegistration;
import ru.devhack.motomoto.sportevents.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping(value = ApiMeta.apiv1 + "/auth")
@Api(tags = {"auth"})
public class AuthController {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthentication> login(@RequestBody AuthenticationRequestModel data) {
        try {
            String employeeCode = data.getEmployeeCode();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employeeCode, data.getPassword()));
            User user = userService.findUserByEmployeeCode(employeeCode)
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + employeeCode + " not found"));
            String token = jwtTokenProvider.createToken(
                    employeeCode,
                    null);

            return ResponseEntity.ok(UserAuthentication.builder()
                        .id(user.getId())
                        .employeeCode(user.getEmployeeCode())
                        .token("Bearer " + token)
                        .result(true)
                        .errorMessage("")
                        .build());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(UserAuthentication.builder()
                            .employeeCode(data.getEmployeeCode())
                            .result(false)
                            .errorMessage(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody UserRegistration userRegistration) {
        try {
            User user = User.builder()
                    .employeeCode(userRegistration.getEmployeeCode())
                    .password(userRegistration.getPassword())
                    .firstName(userRegistration.getFirstName())
                    .middleName(userRegistration.getMiddleName())
                    .lastName(userRegistration.getLastName())
                    .imageUrl(userRegistration.getImageUrl())
                    .role(userRegistration.getRole() != null ? userRegistration.getRole().name() : null)
                    .build();

            return ResponseEntity.ok(userService.convertToModel(userService.registerNew(user)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
