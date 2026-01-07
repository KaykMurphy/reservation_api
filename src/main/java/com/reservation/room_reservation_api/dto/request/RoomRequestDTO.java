package com.reservation.room_reservation_api.dto.request;

import com.reservation.room_reservation_api.domain.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RoomRequestDTO(

        @NotBlank
        String name,

        @NotNull
        @Min(1)
        Integer capacity,

        @NotNull
        @Min(0)
        BigDecimal pricePerHour,

        @NotBlank
        RoomType type

) {
}

