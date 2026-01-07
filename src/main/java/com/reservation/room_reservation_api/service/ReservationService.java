package com.reservation.room_reservation_api.service;

import com.reservation.room_reservation_api.domain.Reservation;
import com.reservation.room_reservation_api.domain.ReservationStatus;
import com.reservation.room_reservation_api.domain.Room;
import com.reservation.room_reservation_api.domain.User;
import com.reservation.room_reservation_api.dto.request.ReservationRequestDTO;
import com.reservation.room_reservation_api.dto.response.ReservationResponseDTO;
import com.reservation.room_reservation_api.mapper.ReservationMapper;
import com.reservation.room_reservation_api.repository.ReservationRepository;
import com.reservation.room_reservation_api.service.validation.ReservationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final ReservationValidator validator;

    @Transactional
    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {

        User user = userService.findUserById(dto.userId());
        Room room = roomService.findRoomById(dto.roomId());

        validator.validate(room, dto.startDateTime(), dto.durationInHours());

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setStartDateTime(dto.startDateTime());
        reservation.setDurationInHours(dto.durationInHours());
        reservation.setTotalPrice(
                room.getPricePerHour().multiply(BigDecimal.valueOf(dto.durationInHours()))
        );
        reservation.setStatus(ReservationStatus.PENDING);

        return ReservationMapper.toDTO(
                reservationRepository.save(reservation)
        );
    }
}
