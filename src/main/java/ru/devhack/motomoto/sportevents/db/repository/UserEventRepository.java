package ru.devhack.motomoto.sportevents.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.devhack.motomoto.sportevents.db.entity.UserEvent;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

public interface UserEventRepository extends JpaRepository<UserEvent, UserEvent.UserEventPK> {
    @Query("from UserEvent ue where ue.userEventPK.userId = :userId")
    List<UserEvent> findAllByUserId(@PathParam("userId") UUID userId);

    @Query("from UserEvent ue where ue.userEventPK.eventId = :eventId")
    List<UserEvent> findAllByEventId(@PathParam("eventId") UUID eventId);

    @Query("from UserEvent ue where ue.userEventPK.userId = :userId and ue.userEventPK.eventId = :eventId")
    List<UserEvent> findAllByUserAndEventId(@PathParam("userId") UUID userId, @PathParam("eventId") UUID eventId);
}
