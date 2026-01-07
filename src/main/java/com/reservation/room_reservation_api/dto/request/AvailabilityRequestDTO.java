package com.reservation.room_reservation_api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AvailabilityRequestDTO(

        @NotNull
        Long roomId,

        @NotNull
        LocalDate date

) {
}
