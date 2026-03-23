package com.hotel.hotel.controller;

import org.springframework.http.ResponseEntity;

// El Controlador es el Mesero así que necesita conocer el Menú (el Modelo)
// Y poder hablar con el Chef (el Service).
// Por lo tanto, es importante importar el modelo y el service

import com.hotel.hotel.model.Reserva;
import com.hotel.hotel.service.ReservaService;

// ===============================
// IMPORTACIONES SPRING
// ===============================
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// ===============================
// VALIDACIONES
// ===============================
import jakarta.validation.Valid;
import java.util.List;

/**
 * ===============================
 * CONTROLLER
 * ===============================
 *
 * Maneja las solicitudes HTTP del cliente.
 *
 * - Se utilizan ResponseEntity
 * - Se aplican códigos HTTP correctos
 * - Se integran validaciones (@Valid)
 * 
 **/

@RestController

@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // ==========================================================
    // LISTA TODAS LAS RESERVAS
    // ==========================================================

    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerReservas() {

        List<Reserva> reservas = reservaService.obtenerTodas();

        return ResponseEntity.ok(reservas); // 200 OK
    }

    // ==========================================================
    // BUSCA POR ID
    // ==========================================================

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReserva(@PathVariable Long id) {

        Reserva reserva = reservaService.obtenerPorId(id);

        return ResponseEntity.ok(reserva); // 200 OK
    }

    // ==========================================================
    // CREA UNA RESERVA
    // ==========================================================

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@Valid @RequestBody Reserva reserva) {

        Reserva nuevaReserva = reservaService.guardar(reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva); // 201
    }

    // ==========================================================
    // ACTUALIZA UNA RESERVA
    // ==========================================================

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody Reserva reserva) {

        Reserva reservaActualizada = reservaService.actualizar(id, reserva);

        return ResponseEntity.ok(reservaActualizada); // 200 OK
    }

    // ==========================================================
    // ELIMINA UNA RESERVA
    // ==========================================================


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {

        reservaService.eliminar(id);

        return ResponseEntity.noContent().build(); // 204
    }

}
