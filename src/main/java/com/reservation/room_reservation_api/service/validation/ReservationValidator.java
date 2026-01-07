package com.reservation.room_reservation_api.service.validation;


import com.reservation.room_reservation_api.domain.ReservationStatus;
import com.reservation.room_reservation_api.domain.Room;
import com.reservation.room_reservation_api.exception.BusinessException;
import com.reservation.room_reservation_api.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReservationValidator {

    private final ReservationRepository reservationRepository;

    public void validate(Room room, LocalDateTime start, Integer duration){
        if (!room.getActive()) {
            throw new BusinessException("A sala precisa estar ativa para reservas.");
        }

        if (start.isBefore(LocalDateTime.now())) {
            throw new BusinessException("Não é possível agendar para o passado.");
        }

        boolean conflict =
                reservationRepository.findByRoomAndStatus(room, ReservationStatus.CONFIRMED)
                        .stream()
                        .anyMatch(r ->
                                start.isBefore(r.getEndDateTime()) &&
                                        start.plusHours(duration).isAfter(r.getStartDateTime())
                        );

        if (conflict) {
            throw new BusinessException("Já existe uma reserva confirmada para este horário.");
        }
    }

}
