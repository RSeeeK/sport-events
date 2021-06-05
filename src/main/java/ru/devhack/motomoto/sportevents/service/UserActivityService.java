package ru.devhack.motomoto.sportevents.service;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.db.entity.UserActivity;
import ru.devhack.motomoto.sportevents.db.repository.UserActivityRepository;
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

    private final UserActivityRepository userActivityRepository;

    public UserActivityService(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public List<ActivityModel> getAllActivity() {
        return activities;
    }

    public UserActivityModel save(@NotNull UserActivityModel userActivityModel) {
        UserActivity userActivity = userActivityRepository.save(convertToUserActivity(userActivityModel));
        userActivityModel.setId(userActivity.getId());
        return userActivityModel;
    }

    public List<UserActivityModel> getByUserId(@NotNull UUID userId) {
        return userActivityRepository.findAllByUserId(userId).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public UserActivity convertToUserActivity(UserActivityModel userActivityModel) {
        return UserActivity.builder()
                .id(userActivityModel.getId())
                .activityDate(userActivityModel.getActivityDate())
                .userId(userActivityModel.getUserId())
                .imageUrl(userActivityModel.getImageUrl())
                .type(userActivityModel.getType() != null ? userActivityModel.getType().name() : null)
                .build();
    }

    public UserActivityModel convertToModel(UserActivity userActivity) {
        return UserActivityModel.builder()
                .id(userActivity.getId())
                .activityDate(userActivity.getActivityDate())
                .imageUrl(userActivity.getImageUrl())
                .userId(userActivity.getUserId())
                .type(userActivity.getType() != null && !userActivity.getType().isEmpty()
                        ? ActivityModel.ActivityType.valueOf(userActivity.getType())
                        : null)
                .build();
    }
}
