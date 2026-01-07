package com.reservation.room_reservation_api.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationRequestDTO(
        @NotNull UUID userId,
        @NotNull Long roomId,
        @Future @NotNull LocalDateTime startDateTime,
        @Min(1) @NotNull Integer durationInHours
) {}


