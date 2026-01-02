package com.reservation.room_reservation_api.service;

import com.reservation.room_reservation_api.domain.User;
import com.reservation.room_reservation_api.dto.UserRequestDTO;
import com.reservation.room_reservation_api.dto.UserResponseDTO;
import com.reservation.room_reservation_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());

        User userSaved = userRepository.save(user);

        return new UserResponseDTO(
                userSaved.getId(),
                userSaved.getName(),
                userSaved.getEmail(),
                userSaved.getPhone()
        );
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPhone()
                ))
                .toList();
    }
}