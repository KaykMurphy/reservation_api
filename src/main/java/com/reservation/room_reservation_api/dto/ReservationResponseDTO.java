package com.reservation.room_reservation_api.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservationResponseDTO (

        Long id,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Integer durationInHours,
        BigDecimal totalPrice,
        String status,
        UserResponseDTO user,
        RoomResponseDTO room
) {
}
