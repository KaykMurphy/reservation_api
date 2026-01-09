package com.reservation.room_reservation_api.controller;

import com.reservation.room_reservation_api.dto.request.RoomRequestDTO;
import com.reservation.room_reservation_api.dto.response.RoomResponseDTO;
import com.reservation.room_reservation_api.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms/")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(@RequestBody @Valid RoomRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(dto));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAllActiveRooms(){

        return ResponseEntity.ok(roomService.getAllActiveRooms());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deactivateRoom(@PathVariable Long id){

        roomService.deactivateRoom(id);
        return ResponseEntity.noContent().build(); // 204

    }


}


