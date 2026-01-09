package com.reservation.room_reservation_api.service;

import com.reservation.room_reservation_api.domain.Reservation;
import com.reservation.room_reservation_api.domain.ReservationStatus;
import com.reservation.room_reservation_api.domain.Room;
import com.reservation.room_reservation_api.domain.User;
import com.reservation.room_reservation_api.dto.request.ReservationRequestDTO;
import com.reservation.room_reservation_api.dto.response.ReservationResponseDTO;
import com.reservation.room_reservation_api.exception.BusinessException;
import com.reservation.room_reservation_api.exception.ResourceNotFoundException;
import com.reservation.room_reservation_api.mapper.ReservationMapper;
import com.reservation.room_reservation_api.repository.ReservationRepository;
import com.reservation.room_reservation_api.repository.UserRepository;
import com.reservation.room_reservation_api.service.validation.ReservationValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final ReservationValidator validator;
    private final UserRepository userRepository;

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



    public ReservationResponseDTO confirmReservation(Long reservationId){

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Reservation not found with id: " + reservationId
                        )
                );

        if (reservation.getStatus() != ReservationStatus.PENDING){
            throw new BusinessException(
                    "Only PENDING reservations can be confirmed"
            );
        }

        validator.validate(
                reservation.getRoom(),
                reservation.getStartDateTime(),
                reservation.getDurationInHours()
        );

        reservation.setStatus(ReservationStatus.CONFIRMED);

        return ReservationMapper.toDTO(
                reservationRepository.save(reservation)
        );

    }



    public ReservationResponseDTO cancelReservation(Long reservationId){

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Reservation not found with id: " + reservationId
                        )
                );

        if (reservation.getStatus() == ReservationStatus.CANCELLED
                || reservation.getStatus() == ReservationStatus.COMPLETED) {
            throw new BusinessException(
                    "Cancelled or completed reservations cannot be confirmed"
            );
        }


        LocalDateTime minimumAllowedTime = LocalDateTime.now().plusHours(24);

        if (reservation.getStartDateTime().isBefore(minimumAllowedTime)) {
            throw new BusinessException(
                    "Reservations can only be cancelled at least 24 hours in advance"
            );
    }

        reservation.setStatus(ReservationStatus.CANCELLED);

        return ReservationMapper.toDTO(
                reservationRepository.save(reservation)
        );
}


    public List<ReservationResponseDTO> getReservationsByUser(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with UUID: " + userId
                        )
                );

        List<Reservation> reservations =
                reservationRepository.findByUserAndStatus(
                        user,
                        ReservationStatus.CONFIRMED
                );

        return reservations.stream()
                .map(ReservationMapper::toDTO)
                .toList();
    }




}
