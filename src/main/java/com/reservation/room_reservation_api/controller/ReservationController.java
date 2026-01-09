package com.reservation.room_reservation_api.controller;

import com.reservation.room_reservation_api.domain.Reservation;
import com.reservation.room_reservation_api.dto.request.AvailabilityRequestDTO;
import com.reservation.room_reservation_api.dto.request.ReservationRequestDTO;
import com.reservation.room_reservation_api.dto.response.ReservationResponseDTO;
import com.reservation.room_reservation_api.dto.response.TimeSlotDTO;
import com.reservation.room_reservation_api.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations/")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO>
    createReservation(@RequestBody @Valid ReservationRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.createReservation(dto));
    }

    @PatchMapping("{id}/confirm")
    public ResponseEntity<ReservationResponseDTO>
    confirmReservation(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.confirmReservation(id));
    }

    @PatchMapping("{id}/cancel")
    public ResponseEntity<ReservationResponseDTO>
    cancelReservation(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>>
    getReservationsByUser(@PathVariable UUID userId){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationsByUser(userId));
    }

/*

TODO
@PostMapping("availability")
    public ResponseEntity<List<TimeSlotDTO>> checkAvailability(@RequestBody @Valid AvailabilityRequestDTO dto) {
        return ResponseEntity.ok(reservationService.checkAvailability(dto));
    }*/








}

