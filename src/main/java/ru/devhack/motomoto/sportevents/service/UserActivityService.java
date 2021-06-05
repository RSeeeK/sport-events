package ru.devhack.motomoto.sportevents.service;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.model.ActivityModel;
import ru.devhack.motomoto.sportevents.model.UserActivityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserActivityService {
    private final List<ActivityModel> activities = new ArrayList<>(); {
        for (ActivityModel.ActivityType type : ActivityModel.ActivityType.values()) {
            activities.add(ActivityModel.builder()
                    .type(type)
                    .description(type.getDescription())
                    .imageUrl("")
                    .build());
        }
    }

    private final List<UserActivityModel> userActivities = new ArrayList<>();

    public List<ActivityModel> getAllActivity() {
        return activities;
    }

    public UserActivityModel save(@NotNull UserActivityModel userActivityModel) {
        userActivities.add(userActivityModel);
        return userActivityModel;
    }

    public List<UserActivityModel> getByUserId(@NotNull UUID userId) {
        return userActivities.stream()
                .filter(userActivityModel -> userActivityModel.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
