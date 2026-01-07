package com.reservation.room_reservation_api.service;

import com.reservation.room_reservation_api.domain.Room;
import com.reservation.room_reservation_api.dto.request.RoomRequestDTO;
import com.reservation.room_reservation_api.dto.response.RoomResponseDTO;
import com.reservation.room_reservation_api.exception.ResourceNotFoundException;
import com.reservation.room_reservation_api.mapper.RoomMapper;
import com.reservation.room_reservation_api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomResponseDTO createRoom(RoomRequestDTO dto) {

        Room room = new Room();
        room.setName(dto.name());
        room.setCapacity(dto.capacity());
        room.setType(dto.type());
        room.setPricePerHour(dto.pricePerHour());
        room.setActive(true);

        return RoomMapper.toDTO(
                roomRepository.save(room)
        );
    }

    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sala não encontrada com ID: " + id)
                );
    }

    public List<RoomResponseDTO> getAllActiveRooms() {
        return roomRepository.findByActiveTrue()
                .stream()
                .map(RoomMapper::toDTO)
                .toList();
    }

    public void deactivateRoom(Long id) {
        Room room = findRoomById(id);
        room.setActive(false);
        roomRepository.save(room);
    }
}


