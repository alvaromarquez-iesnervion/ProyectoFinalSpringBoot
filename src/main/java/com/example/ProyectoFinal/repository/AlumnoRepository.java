package com.example.ProyectoFinal.repository;

import com.example.ProyectoFinal.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de acceso a datos de la entidad Alumno
 * Extiende JpaRepository que proporciona métodos CRUD básicos y soporte para paginación
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    /**
     * Busca alumnos cuyo nombre contenga la cadena especificada (ignorando mayúsculas/minúsculas)
     * 
     * @param nombre Texto a buscar en el nombre del alumno
     * @return Lista de alumnos que coinciden con el criterio de búsqueda
     */
    List<Alumno> findByNombreContainingIgnoreCase(String nombre);
}
