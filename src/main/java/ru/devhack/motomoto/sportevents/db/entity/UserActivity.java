package ru.devhack.motomoto.sportevents.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devhack.motomoto.sportevents.db.meta.SportEventsDBMeta;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = SportEventsDBMeta.schema, name = SportEventsDBMeta.user_activity.name)
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = SportEventsDBMeta.user_activity.fld.id)
    private UUID id;

    @Column(name = SportEventsDBMeta.user_activity.fld.user_id)
    private UUID userId;

    @Column(name = SportEventsDBMeta.user_activity.fld.type)
    private String type;

    @Column(name = SportEventsDBMeta.user_activity.fld.activity_date)
    private LocalDateTime activityDate;

    @Column(name = SportEventsDBMeta.user_activity.fld.image_url)
    private String imageUrl;
}
