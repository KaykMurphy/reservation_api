package com.reservation.room_reservation_api.dto.response;

import java.time.LocalDateTime;

public record TimeSlotDTO(

        LocalDateTime startTime,
        LocalDateTime endTime,
        Boolean available

) {
}

