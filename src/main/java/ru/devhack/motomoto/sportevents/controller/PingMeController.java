package ru.devhack.motomoto.sportevents.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.devhack.motomoto.sportevents.db.repository.PingMeRepository;

@CrossOrigin(origins = "*", maxAge = 3600 * 24 * 7)
@RestController
@RequestMapping(ApiMeta.apiv1 + "/test")
public class PingMeController {

    private final PingMeRepository pingMeRepository;

    public PingMeController(PingMeRepository pingMeRepository) {
        this.pingMeRepository = pingMeRepository;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Database: " + (pingMeRepository.ping() > 0);
    }
}
