package com.reservation.room_reservation_api.mapper;

import com.reservation.room_reservation_api.domain.Room;
import com.reservation.room_reservation_api.dto.response.RoomResponseDTO;

public class RoomMapper {
    public static RoomResponseDTO toDTO(Room room) {
        return new RoomResponseDTO(
                room.getId(),
                room.getName(),
                room.getCapacity(),
                room.getPricePerHour(),
                room.getType(),
                room.getActive()
        );
    }
}
