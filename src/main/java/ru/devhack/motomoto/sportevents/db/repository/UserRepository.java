package ru.devhack.motomoto.sportevents.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.devhack.motomoto.sportevents.db.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmployeeCode(String employeeCode);

    @Query("from User")
    List<User> findAll();
}
