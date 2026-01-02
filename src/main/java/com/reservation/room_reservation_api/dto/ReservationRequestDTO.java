package com.reservation.room_reservation_api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationRequestDTO(

        @NotNull
        UUID userId,

        @NotNull
        Long roomId,

        @Future(message = "A data de reserva deve ser futura")
        @NotNull
        LocalDateTime startDateTime,

        @Min(1)
        @NotNull
        Integer durationInHours

) {
}
/*
- `UUID userId` (não nulo)
        - `Long roomId` (não nulo)
        - `LocalDateTime startDateTime` (não nulo, deve ser futuro)
        - `Integer durationInHours` (não nulo, mínimo 1)*/
