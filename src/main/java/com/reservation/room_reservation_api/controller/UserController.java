package com.reservation.room_reservation_api.controller;

import com.reservation.room_reservation_api.dto.request.UserRequestDTO;
import com.reservation.room_reservation_api.dto.response.UserResponseDTO;
import com.reservation.room_reservation_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody @Valid UserRequestDTO dto) {

        UserResponseDTO response = userService.createUser(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> response = userService.getAllUsers();

        return ResponseEntity
                .ok(response);
    }
}

