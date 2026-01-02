package com.reservation.room_reservation_api.repository;

import com.reservation.room_reservation_api.domain.Reservation;
import com.reservation.room_reservation_api.domain.ReservationStatus;
import com.reservation.room_reservation_api.domain.Room;
import com.reservation.room_reservation_api.domain.User;
import jdk.jshell.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoomAndStatus(Room room, ReservationStatus status);
    List<Reservation> findByUserAndStatus(User user,ReservationStatus status);

}
