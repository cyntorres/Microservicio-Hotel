package com.hotel.hotel.service;

// ===============================
// IMPORTACIONES DEL PROYECTO
// ===============================
// Se importa la entidad Reserva, que representa cada registro de la tabla RESERVAS.
import com.hotel.hotel.model.Reserva;

// Se importa el repository, que se encarga de la comunicación con la base de datos.
import com.hotel.hotel.repository.ReservaRepository;
import com.hotel.hotel.exception.ResourceNotFoundException;

// ===============================
// IMPORTACIONES DE SPRING
// ===============================
// Marca esta clase como un servicio dentro del contexto de Spring.
import org.springframework.stereotype.Service;

// ===============================
// IMPORTACIONES DE LOMBOK
// ===============================
// @Slf4j permite registrar logs de manera profesional.
import lombok.extern.slf4j.Slf4j;

// ===============================
// IMPORTACIONES DE JAVA
// ===============================
import java.util.List;

/**
 * ===============================
 * SERVICIO
 * ===============================
 *
 * Esta clase contiene la lógica de negocio del microservicio.
 */

@Service
@Slf4j
public class ReservaService {

    /**
     * Referencia al repository.
     * Esta variable permitirá acceder a los métodos CRUD provistos por JPA.
     */
    private final ReservaRepository reservaRepository;

    /**
     * Constructor con inyección de dependencias.
     * Spring Boot entrega automáticamente una instancia de LibroRepository.
     */
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    // ==========================================================
    // OBTIENE TODAS LAS RESERVAS
    // ==========================================================

    /**
     * Este método recupera todos las reservas registradas en la base de datos.
     *
     * @return lista de reservas
     */
    public List<Reserva> obtenerTodas() {
        log.info("Solicitando el listado completo de reservas.");

        List<Reserva> reservas = reservaRepository.findAll();

        log.info("Se encontraron {} reservas registradas.", reservas.size());
        return reservas;
    }

    // ==========================================================
    // OBTIENE UNA RESERVA POR SU ID
    // ==========================================================

    /**
     * Este método busca una reserva por su id.
     * Si no existe, se lanza una excepción personalizada.
     *
     * @param id identificador de la reserva
     * @return reserva encontrada
     */
    public Reserva obtenerPorId(Long id) {
        log.info("Buscando la reserva ID: {}", id);

        return reservaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró un libro con ID: {}", id);
                    return new ResourceNotFoundException("Libro no encontrado con ID: " + id);
                });
    }

    // ==========================================================
    // GUARDA UNA RESERVA
    // ==========================================================

    /**
     * Este método guarda una reserva en la base de datos.
     *
     * @param reserva reserva a registrar
     * @return reserva guardada
     */
    public Reserva guardar(Reserva reserva) {
        log.info("Guardando reserva: {}", reserva.getTipo_habitacion());

        Reserva reservaGuardada = reservaRepository.save(reserva);

        log.info("Libro guardado correctamente con ID: {}", reservaGuardada.getId());
        return reservaGuardada;
    }

    // ==========================================================
    // ELIMINA UNA RESERVA POR SU ID
    // ==========================================================

    /**
     * Este método elimina una reserva utilizando su id.
     * Si la reserva no existe, se lanza una excepción personalizada.
     *
     * @param id identificador de la reserva a eliminar
     */
    public void eliminar(Long id) {
        log.info("Intentando eliminar reserva ID: {}", id);

        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se puede eliminar. Reserva no encontrada, ID: {}", id);
                    return new ResourceNotFoundException("Reserva no encontrada, ID: " + id);
                });

        reservaRepository.delete(reservaExistente);

        log.info("Reserva eliminada correctamente, ID: {}", id);
    }

    // ==========================================================
    // ACTUALIZA UNA RESERVA EXISTENTE
    // ==========================================================

    /**
     * Este método actualiza una reserva existente a partir de su id.
     * Si la reserva no existe, se lanza una excepción personalizada.
     *
     * @param id                 identificador de la reserva a actualizar
     * @param reservaActualizada datos nuevos de la reserva
     * @return reserva actualizada
     */
    public Reserva actualizar(Long id, Reserva reservaActualizada) {
        log.info("Intentando actualizar reserva ID: {}", id);

        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se puede actualizar. Reserva no encontrada, ID: {}", id);
                    return new ResourceNotFoundException("Reserva no encontrada, ID: " + id);
                });
        // Se actualizan los campos con los nuevos valores recibidos.
        // Seteamos los valores de la reserva que viene de la base de datos
        // con los nuevos datos que recibimos en el objeto 'reservaActualizada'

        reservaExistente.setCliente_id(reservaActualizada.getCliente_id());
        reservaExistente.setHabitacion_id(reservaActualizada.getHabitacion_id());
        reservaExistente.setTipo_habitacion(reservaActualizada.getTipo_habitacion());
        reservaExistente.setFecha_reserva(reservaActualizada.getFecha_reserva());
        reservaExistente.setEstado(reservaActualizada.getEstado());
        reservaExistente.setCant_personas(reservaActualizada.getCant_personas());

        Reserva reservaGuardada = reservaRepository.save(reservaExistente);

        log.info("Libro actualizado correctamente con ID: {}", reservaGuardada.getId());
        return reservaGuardada;
    }

}