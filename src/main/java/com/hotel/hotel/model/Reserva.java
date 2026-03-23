package com.hotel.hotel.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// ===============================
// IMPORTACIONES DE VALIDACIÓN
// ===============================
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "RESERVA")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long cliente_id;

    @NotNull(message = "El ID de la habitación es obligatorio")
    private Long habitacion_id;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    @Size(min = 3, max = 50, message = "El tipo de habitación debe tener entre 3 y 50 caracteres")
    private String tipo_habitacion;

    @NotNull(message = "La fecha de la reserva es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
    private Date fecha_reserva; 

    @NotBlank(message = "El estado de la reserva es obligatorio")
    private String estado; 

    @NotNull(message = "La cantidad de personas es obligatoria")
    @Min(value = 1, message = "Debe haber al menos 1 persona")
    @Max(value = 10, message = "No se permiten más de 10 personas por reserva")
    private Long cant_personas;

    public Reserva() {
    }

     public Reserva(Long id, Long cliente_id, Long habitacion_id, String tipo_habitacion, Date fecha_reserva, String estado, Long cant_personas){
        this.id = id;
        this.cliente_id = cliente_id;
        this.habitacion_id = habitacion_id;
        this.tipo_habitacion = tipo_habitacion;
        this.fecha_reserva = fecha_reserva;
        this.estado = estado; 
        this.cant_personas = cant_personas;
    }

    // --- GETTERS Y SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Long getHabitacion_id() {
        return habitacion_id;
    }

    public void setHabitacion_id(Long habitacion_id) {
        this.habitacion_id = habitacion_id;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(Long cant_personas) {
        this.cant_personas = cant_personas;
    }
}