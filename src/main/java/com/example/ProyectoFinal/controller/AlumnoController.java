package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.entity.Alumno;
import com.example.ProyectoFinal.service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de Alumnos
 * Expone endpoints para crear, leer, actualizar y eliminar alumnos
 * Incluye funcionalidades adicionales de búsqueda y paginación
 */
@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "Alumnos", description = "API para gestión de alumnos")
@SecurityRequirement(name = "basicAuth")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    /**
     * Obtiene todos los alumnos sin paginación
     */
    @Operation(
        summary = "Obtener todos los alumnos",
        description = "Retorna una lista completa de todos los alumnos registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de alumnos obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = Alumno.class))
        )
    })
    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        List<Alumno> alumnos = alumnoService.getAllAlumnos();
        return ResponseEntity.ok(alumnos);
    }

    /**
     * Obtiene alumnos con paginación y ordenamiento
     */
    @Operation(
        summary = "Obtener alumnos con paginación",
        description = "Retorna una página de alumnos con soporte para ordenamiento. " +
                     "Parámetros: page (número de página, inicia en 0), size (elementos por página), " +
                     "sort (campo de ordenamiento, ej: nombre,asc)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Página de alumnos obtenida exitosamente"
        )
    })
    @GetMapping("/paginated")
    public ResponseEntity<Page<Alumno>> getAllAlumnosPaginated(
            @Parameter(description = "Configuración de paginación y ordenamiento")
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Alumno> alumnos = alumnoService.getAllAlumnosPaginated(pageable);
        return ResponseEntity.ok(alumnos);
    }

    /**
     * Obtiene un alumno por su ID
     */
    @Operation(
        summary = "Obtener alumno por ID",
        description = "Retorna un alumno específico buscado por su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Alumno encontrado exitosamente",
            content = @Content(schema = @Schema(implementation = Alumno.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Alumno no encontrado con el ID especificado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(
            @Parameter(description = "ID del alumno a buscar", required = true)
            @PathVariable Long id) {
        Alumno alumno = alumnoService.getAlumnoById(id);
        return ResponseEntity.ok(alumno);
    }

    /**
     * Busca alumnos por nombre (búsqueda parcial)
     */
    @Operation(
        summary = "Buscar alumnos por nombre",
        description = "Busca alumnos cuyo nombre contenga el texto especificado (no distingue mayúsculas/minúsculas)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Búsqueda completada exitosamente",
            content = @Content(schema = @Schema(implementation = Alumno.class))
        )
    })
    @GetMapping("/search")
    public ResponseEntity<List<Alumno>> searchAlumnosByNombre(
            @Parameter(description = "Texto a buscar en el nombre del alumno", required = true)
            @RequestParam String nombre) {
        List<Alumno> alumnos = alumnoService.searchAlumnosByNombre(nombre);
        return ResponseEntity.ok(alumnos);
    }

    /**
     * Crea un nuevo alumno
     */
    @Operation(
        summary = "Crear nuevo alumno",
        description = "Registra un nuevo alumno en el sistema. La fecha de registro se asigna automáticamente."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Alumno creado exitosamente",
            content = @Content(schema = @Schema(implementation = Alumno.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos (validación fallida)"
        )
    })
    @PostMapping
    public ResponseEntity<Alumno> createAlumno(
            @Parameter(description = "Datos del alumno a crear", required = true)
            @Valid @RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoService.createAlumno(alumno);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }

    /**
     * Actualiza un alumno existente
     */
    @Operation(
        summary = "Actualizar alumno existente",
        description = "Actualiza los datos de un alumno existente. La fecha de registro no se modifica."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Alumno actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = Alumno.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Alumno no encontrado con el ID especificado"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(
            @Parameter(description = "ID del alumno a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos del alumno", required = true)
            @Valid @RequestBody Alumno alumnoDetails) {
        Alumno alumnoActualizado = alumnoService.updateAlumno(id, alumnoDetails);
        return ResponseEntity.ok(alumnoActualizado);
    }

    /**
     * Elimina un alumno
     */
    @Operation(
        summary = "Eliminar alumno",
        description = "Elimina un alumno del sistema de forma permanente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Alumno eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Alumno no encontrado con el ID especificado"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(
            @Parameter(description = "ID del alumno a eliminar", required = true)
            @PathVariable Long id) {
        alumnoService.deleteAlumno(id);
        return ResponseEntity.noContent().build();
    }
}
