package ru.devhack.motomoto.sportevents.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.devhack.motomoto.sportevents.db.entity.Event;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
