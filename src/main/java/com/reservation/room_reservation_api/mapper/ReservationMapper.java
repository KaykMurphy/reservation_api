package com.reservation.room_reservation_api.mapper;

import com.reservation.room_reservation_api.domain.Reservation;
import com.reservation.room_reservation_api.dto.response.ReservationResponseDTO;

public class ReservationMapper {
    public static ReservationResponseDTO toDTO(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getStartDateTime(),
                reservation.getEndDateTime(),
                reservation.getDurationInHours(),
                reservation.getTotalPrice(),
                reservation.getStatus().name(),
                UserMapper.toDTO(reservation.getUser()),
                RoomMapper.toDTO(reservation.getRoom())
        );
    }
}
