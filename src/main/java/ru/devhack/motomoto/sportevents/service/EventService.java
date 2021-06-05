package ru.devhack.motomoto.sportevents.service;

import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.model.EventModel;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    private final List<EventModel> data = new ArrayList<>(); {
        data.add(EventModel.builder()
                .id(UUID.fromString("f5b45eaa-5cab-4e82-8a7a-bb62dd0278c1"))
                .eventDate(LocalDateTime.of(2021, Month.AUGUST, 23, 12, 30))
                .registrationOver(false)
                .address("Москва, Ленинский проспект 20")
                .name("Краткое описания мероприятия 1")
                .description("Тут какое-то очень длинное описание: " +
                        "опавпол дваолдпол ваплодпваолд лопваол" +
                        "пдвалджпвалдж пвлджаджлпвджл пвдлжалджпв" +
                        "плвалдпв лджлджпвалджпвад жлпвадлж лджпва" +
                        "пвал длджпвалжд пвадлжваплджвп алджпвалдж" +
                        "аыволыовалдол давыолды ваолд.")
                .imageUrl("https://topcelebration.ru/wp-content/uploads/2015/07/4.%D0%9A%D0%BE%D1%80%D0%BF%D0%BE%D1%80%D0%B0%D1%82%D0%B8%D0%B2.jpg")
                .limit(100)
                .build());
        data.add(EventModel.builder()
                .id(UUID.fromString("2268c91e-a158-467b-8a66-9779a1a9eefd"))
                .eventDate(LocalDateTime.of(2021, Month.SEPTEMBER, 15, 20, 15))
                .registrationOver(false)
                .address("Санкт-Петербург, Дворцовая площадь")
                .name("Краткое описания мероприятия 2")
                .description("Тут какое-то очень длинное описание: " +
                        "опавпол дваолдпол ваплодпваолд лопваол" +
                        "пдвалджпвалдж пвлджаджлпвджл пвдлжалджпв" +
                        "плвалдпв лджлджпвалджпвад жлпвадлж лджпва" +
                        "пвал длджпвалжд пвадлжваплджвп алджпвалдж" +
                        "аыволыовалдол давыолды ваолд.")
                .imageUrl("https://topcelebration.ru/wp-content/uploads/2015/07/4.%D0%9A%D0%BE%D1%80%D0%BF%D0%BE%D1%80%D0%B0%D1%82%D0%B8%D0%B2.jpg")
                .limit(100)
                .build());
        data.add(EventModel.builder()
                .id(UUID.fromString("62b5f20d-5cd8-46ec-806a-b8ad41d99c80"))
                .eventDate(LocalDateTime.of(2021, Month.JULY, 11, 17, 50))
                .registrationOver(true)
                .address("Самара, Красноармейская улица 93")
                .name("Краткое описания мероприятия 3")
                .description("Тут какое-то очень длинное описание: " +
                        "опавпол дваолдпол ваплодпваолд лопваол" +
                        "пдвалджпвалдж пвлджаджлпвджл пвдлжалджпв" +
                        "плвалдпв лджлджпвалджпвад жлпвадлж лджпва" +
                        "пвал длджпвалжд пвадлжваплджвп алджпвалдж" +
                        "аыволыовалдол давыолды ваолд.")
                .imageUrl("https://topcelebration.ru/wp-content/uploads/2015/07/4.%D0%9A%D0%BE%D1%80%D0%BF%D0%BE%D1%80%D0%B0%D1%82%D0%B8%D0%B2.jpg")
                .limit(100)
                .build());
    }

    public List<EventModel> getAll() {
        return data;
    }

    public EventModel save(EventModel eventModel) {
        eventModel.setId(UUID.randomUUID());
        data.add(eventModel);
        return eventModel;
    }
}
