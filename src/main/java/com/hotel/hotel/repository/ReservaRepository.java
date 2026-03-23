package com.hotel.hotel.repository;

// Importo la clase Reserva para que sea gestionada por el repository.
import com.hotel.hotel.model.Reserva;

// JpaRepository proporciona métodos CRUD ya implementados automáticamente.
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<Reserva, Long> significa:
 * - Reserva → la entidad que se manejará
 * - Long → el tipo de dato de la clave primaria (id)
 *
 * Métodos disponibles automáticamente:
 * - save() → guardar o actualizar
 * - findAll() → listar todos
 * - findById() → buscar por id
 * - deleteById() → eliminar por id
 **/

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
