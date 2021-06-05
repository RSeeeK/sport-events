package ru.devhack.motomoto.sportevents.service;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.devhack.motomoto.sportevents.db.entity.User;
import ru.devhack.motomoto.sportevents.db.repository.UserRepository;
import ru.devhack.motomoto.sportevents.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final List<UserModel> data = new ArrayList<>(); {
        data.add(UserModel.builder()
                .id(UUID.fromString("fc75510b-1d44-40cd-8b2a-020395287612"))
                .employeeCode("T4359-FDF")
                .firstName("Петр")
                .middleName("Александрович")
                .lastName("Птушкин")
                .imageUrl("https://fb.ru/media/i/7/5/7/5/4/8/i/757548.jpg")
                .role(UserModel.UserRole.PARTICIPANT)
                .build());
        data.add(UserModel.builder()
                .id(UUID.fromString("f4b40b03-79e2-41d9-8223-952cffea90c7"))
                .employeeCode("YGFD5-4G5")
                .firstName("Сергей")
                .middleName("Владимирович")
                .lastName("Шнуров")
                .imageUrl("https://tvcenter.ru/wp-content/uploads/2020/11/65-1.jpg")
                .role(UserModel.UserRole.EVENT_MAKER)
                .build());
    }

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserModel> getAll() {
        return data;
    }

    public Optional<UserModel> findUserById(@NotNull UUID id) {
        return data.stream()
                .filter(userModel -> userModel.getId().equals(id))
                .findFirst();
    }

    public User registerNew(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public Optional<User> findUserByEmployeeCode(String employeeCode) {
        return userRepository.findByEmployeeCode(employeeCode);
    }
}
