package ru.devhack.motomoto.sportevents.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devhack.motomoto.sportevents.db.meta.SportEventsDBMeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = SportEventsDBMeta.schema, name = SportEventsDBMeta.event.name)
public class Event {
    @Id
    @Column(name = SportEventsDBMeta.event.fld.id)
    private UUID id;

    @Column(name = SportEventsDBMeta.event.fld.name)
    private String name;

    @Column(name = SportEventsDBMeta.event.fld.event_date)
    private LocalDateTime eventDate;

    @Column(name = SportEventsDBMeta.event.fld.registration_over)
    private Boolean registrationOver;

    @Column(name = SportEventsDBMeta.event.fld.limit)
    private Integer limit;

    @Column(name = SportEventsDBMeta.event.fld.description)
    private String description;

    @Column(name = SportEventsDBMeta.event.fld.address)
    private String address;

    @Column(name = SportEventsDBMeta.event.fld.image_url)
    private String imageUrl;
}
