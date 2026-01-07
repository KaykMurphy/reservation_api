package com.reservation.room_reservation_api.mapper;

import com.reservation.room_reservation_api.domain.User;
import com.reservation.room_reservation_api.dto.response.UserResponseDTO;

public class UserMapper {
    public static UserResponseDTO toDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
