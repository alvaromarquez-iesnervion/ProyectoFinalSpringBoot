package com.example.ProyectoFinal.service;

import com.example.ProyectoFinal.entity.Alumno;
import com.example.ProyectoFinal.exception.ResourceNotFoundException;
import com.example.ProyectoFinal.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio que contiene la lógica de negocio para gestionar Alumnos
 * Actúa como intermediario entre el controlador y el repositorio
 */
@Service
@Transactional
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    /**
     * Obtiene todos los alumnos registrados en el sistema
     * 
     * @return Lista completa de alumnos
     */
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    /**
     * Obtiene alumnos con paginación y ordenamiento
     * 
     * @param pageable Configuración de paginación (página, tamaño, ordenamiento)
     * @return Página de alumnos según los parámetros especificados
     */
    public Page<Alumno> getAllAlumnosPaginated(Pageable pageable) {
        return alumnoRepository.findAll(pageable);
    }

    /**
     * Busca un alumno por su ID
     * 
     * @param id Identificador único del alumno
     * @return Alumno encontrado
     * @throws ResourceNotFoundException si el alumno no existe
     */
    public Alumno getAlumnoById(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alumno no encontrado con ID: " + id));
    }

    /**
     * Busca alumnos cuyo nombre contenga el texto especificado
     * La búsqueda no distingue entre mayúsculas y minúsculas
     * 
     * @param nombre Texto a buscar en el nombre
     * @return Lista de alumnos que coinciden con el criterio
     */
    public List<Alumno> searchAlumnosByNombre(String nombre) {
        return alumnoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Crea un nuevo alumno en el sistema
     * La fecha de registro se establece automáticamente
     * 
     * @param alumno Datos del alumno a crear
     * @return Alumno creado con su ID asignado
     */
    public Alumno createAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    /**
     * Actualiza los datos de un alumno existente
     * 
     * @param id Identificador del alumno a actualizar
     * @param alumnoDetails Nuevos datos del alumno
     * @return Alumno actualizado
     * @throws ResourceNotFoundException si el alumno no existe
     */
    public Alumno updateAlumno(Long id, Alumno alumnoDetails) {
        Alumno alumno = getAlumnoById(id);
        
        alumno.setNombre(alumnoDetails.getNombre());
        alumno.setEmail(alumnoDetails.getEmail());
        // La fecha de registro no se modifica
        
        return alumnoRepository.save(alumno);
    }

    /**
     * Elimina un alumno del sistema
     * 
     * @param id Identificador del alumno a eliminar
     * @throws ResourceNotFoundException si el alumno no existe
     */
    public void deleteAlumno(Long id) {
        Alumno alumno = getAlumnoById(id);
        alumnoRepository.delete(alumno);
    }
}
