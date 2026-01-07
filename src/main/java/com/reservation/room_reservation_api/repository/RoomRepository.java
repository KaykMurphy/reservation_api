package com.reservation.room_reservation_api.repository;

import com.reservation.room_reservation_api.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByActiveTrue();

    Room findRoomById(Long id);
}
