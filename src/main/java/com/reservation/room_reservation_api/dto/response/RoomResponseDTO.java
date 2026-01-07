package com.reservation.room_reservation_api.dto.response;

import java.math.BigDecimal;

public record RoomResponseDTO(

        Long id,
        String name,
        Integer capacity,
        BigDecimal pricePerHour,
        com.reservation.room_reservation_api.domain.RoomType type,
        Boolean active

) {
}


