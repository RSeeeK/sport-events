package ru.devhack.motomoto.sportevents.service;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.model.ActivityModel;
import ru.devhack.motomoto.sportevents.model.UserActivity;

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

    private final List<UserActivity> userActivities = new ArrayList<>();

    public List<ActivityModel> getAllActivity() {
        return activities;
    }

    public UserActivity save(@NotNull UserActivity userActivity) {
        userActivities.add(userActivity);
        return userActivity;
    }

    public List<UserActivity> getByUserId(@NotNull UUID userId) {
        return userActivities.stream()
                .filter(userActivity -> userActivity.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
