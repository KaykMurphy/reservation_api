package com.reservation.room_reservation_api.service;

import com.reservation.room_reservation_api.domain.Room;
import com.reservation.room_reservation_api.dto.RoomRequestDTO;
import com.reservation.room_reservation_api.dto.RoomResponseDTO;
import com.reservation.room_reservation_api.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomResponseDTO createRoom(RoomRequestDTO dto){
        Room room = new Room();
        room.setName(dto.name());
        room.setCapacity(dto.capacity());
        room.setType(dto.type());
        room.setPricePerHour(dto.pricePerHour());
        room.setActive(true);

        Room roomSaved = roomRepository.save(room);

        return new RoomResponseDTO(
                roomSaved.getId(),
                roomSaved.getName(),
                roomSaved.getCapacity(),
                roomSaved.getPricePerHour(),
                roomSaved.getType(),
                room.getActive()
        );
    }

    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada com ID: " + id));
    }

    public List<RoomResponseDTO> getAllActiveRooms() {

        return roomRepository.findByActiveTrue(true)
                .stream()
                .map(room -> new RoomResponseDTO(
                        room.getId(),
                        room.getName(),
                        room.getCapacity(),
                        room.getPricePerHour(),
                        room.getType(),
                        room.getActive()
                )).toList();

    }

    public void deactivateRoom(Long id){
        Room room = roomRepository.findRoomById(id);

        room.setActive(false);

        roomRepository.save(room);

    }






}

