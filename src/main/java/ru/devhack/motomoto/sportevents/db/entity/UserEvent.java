package ru.devhack.motomoto.sportevents.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devhack.motomoto.sportevents.db.meta.SportEventsDBMeta;
import ru.devhack.motomoto.sportevents.model.UserEventModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = SportEventsDBMeta.schema, name = SportEventsDBMeta.user_event.name)
public class UserEvent {
    @EmbeddedId
    private UserEventPK userEventPK;

    @Enumerated(EnumType.STRING)
    @Column(name = SportEventsDBMeta.user_event.fld.participation_type)
    private UserEventModel.ParticipationType participationType;

    @Column(name = SportEventsDBMeta.user_event.fld.approved)
    private Boolean approved;

    @Embeddable
    public static class UserEventPK implements Serializable {
        @Column(name = SportEventsDBMeta.user_event.fld.event_id)
        private UUID eventId;
        @Column(name = SportEventsDBMeta.user_event.fld.user_id)
        private UUID userId;
    }
}
