package ru.devhack.motomoto.sportevents.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.devhack.motomoto.sportevents.db.entity.UserActivity;

import java.util.List;
import java.util.UUID;

public interface UserActivityRepository extends JpaRepository<UserActivity, UUID> {
    @Query("from UserActivity ua where ua.userId = :userId")
    List<UserActivity> findAllByUserId(@Param("userId") UUID userId);
}
