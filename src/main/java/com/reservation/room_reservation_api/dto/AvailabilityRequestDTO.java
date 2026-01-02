package com.reservation.room_reservation_api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AvailabilityRequestDTO(

        @NotNull
        Long roomId,

        @NotNull
        LocalDate date

) {
}
