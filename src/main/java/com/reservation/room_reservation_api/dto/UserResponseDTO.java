package com.reservation.room_reservation_api.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String phone
) {
}
