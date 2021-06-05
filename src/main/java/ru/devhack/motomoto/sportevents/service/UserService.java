package ru.devhack.motomoto.sportevents.service;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.db.entity.User;
import ru.devhack.motomoto.sportevents.db.repository.UserRepository;
import ru.devhack.motomoto.sportevents.model.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserModel> getAll() {
        return userRepository.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public Optional<UserModel> findUserById(@NotNull UUID id) {
        return userRepository.findById(id)
                .map(this::convertToModel);
    }

    public User registerNew(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public Optional<User> findUserByEmployeeCode(String employeeCode) {
        return userRepository.findByEmployeeCode(employeeCode);
    }

    public UserModel convertToModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .employeeCode(user.getEmployeeCode())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .role(user.getRole() != null && !user.getRole().isEmpty() ? UserModel.UserRole.valueOf(user.getRole()) : null)
                .build();
    }

    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }
}
