package com.example.ProyectoFinal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * Entidad que representa un Alumno en el sistema
 * Contiene información básica del estudiante y su fecha de registro
 */
@Entity
@Table(name = "alumnos")
public class Alumno {

    /**
     * Identificador único del alumno
     * Generado automáticamente por la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del alumno
     * Campo obligatorio - no puede estar vacío
     */
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * Correo electrónico del alumno
     * Debe ser un formato válido de email y es obligatorio
     */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    /**
     * Fecha de registro del alumno en el sistema
     * Se establece automáticamente al crear el registro
     */
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    // -------------------------
    // Constructores
    // -------------------------

    public Alumno() {
    }

    public Alumno(Long id, String nombre, String email, LocalDate fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    // -------------------------
    // Getters y Setters
    // -------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // -------------------------
    // toString
    // -------------------------

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }

    /**
     * Método que se ejecuta automáticamente antes de persistir la entidad
     * Establece la fecha de registro con la fecha actual si no está definida
     */
    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDate.now();
        }
    }
}
