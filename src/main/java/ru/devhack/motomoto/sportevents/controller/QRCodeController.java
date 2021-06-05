package ru.devhack.motomoto.sportevents.controller;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.devhack.motomoto.sportevents.model.UserEventModel;
import ru.devhack.motomoto.sportevents.service.UserEventService;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600 * 24 * 7)
@RestController
@RequestMapping(ApiMeta.apiv1 + "/qr")
public class QRCodeController {
    private final UserEventService userEventService;

    public QRCodeController(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @GetMapping(value = "/approve/{qrCodeKey}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Подтвердить участие пользователя по ключу QR")
    public @ResponseBody
    @NotNull
    ResponseEntity<UserEventModel> approveUserParticipation(@PathVariable String qrCodeKey) {
        String[] parts = qrCodeKey.split("_");
        if (parts.length == 2) {
            UUID userId = UUID.fromString(parts[0]);
            UUID eventId = UUID.fromString(parts[1]);
            Optional<UserEventModel> userEventModelOptional = userEventService.getByUserEventId(userId, eventId);
            if (userEventModelOptional.isPresent()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userEventService.approveUserParticipation(userEventModelOptional.get()));
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
