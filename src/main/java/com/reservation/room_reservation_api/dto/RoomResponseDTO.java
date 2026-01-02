package com.reservation.room_reservation_api.dto;

import java.math.BigDecimal;

public record RoomResponseDTO(

        Long id,
        String name,
        Integer capacity,
        BigDecimal pricePerHour,
        String type,
        Boolean active

) {
}


