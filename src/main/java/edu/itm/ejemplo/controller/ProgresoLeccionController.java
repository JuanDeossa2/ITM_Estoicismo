package edu.itm.ejemplo.controller;

import edu.itm.ejemplo.entitiesSQL.Lecciones;
import edu.itm.ejemplo.entitiesSQL.ProgresoLecciones;
import edu.itm.ejemplo.services.ProgresoLeccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progreso")
public class ProgresoLeccionController {

    @Autowired
    private ProgresoLeccionService service;

    @Operation(
            tags = {"Progresos"},
            summary = "Permite obtener la lista de Progresos",
            description = "Obtiene todos los Progresos desde la base de datos",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Lista de Progresos",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ProgresoLecciones.class)
                                    )
                            }
                    )
            }
    )

    @GetMapping("/listarProgresos")
    public ResponseEntity<List<ProgresoLecciones>> listarProgreso() {
        return new ResponseEntity<>(service.listarProgreso(), HttpStatus.OK);
    }
    @Operation(
            tags = {"Progresos"},
            summary = "Permite insertar un nuevo progreso",
            description = "Inserta un nuevo progreso en la base de datos"
    )
    @PostMapping("/crearProgreso")
    public ResponseEntity<ProgresoLecciones> crearProgreso(@RequestBody ProgresoLecciones progreso) {

        if (ObjectUtils.isEmpty(progreso) ||
                progreso.getIdUsuario() == 0 ||
                progreso.getIdLeccion() == 0) {
            return new ResponseEntity<>(progreso, HttpStatus.BAD_REQUEST);
        }

        progreso = service.crearProgreso(progreso);

        if (ObjectUtils.isEmpty(progreso)) {
            return new ResponseEntity<>(progreso, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(progreso, HttpStatus.CREATED);
    }
    @Operation(
            tags = {"Progresos"},
            summary = "Permite actualizar un progreso",
            description = "Actualiza la información de un progreso existente"
    )
    @PutMapping("/actualizarProgreso")
    public ResponseEntity<ProgresoLecciones> actualizarProgreso(@RequestBody ProgresoLecciones progreso) {

        if (ObjectUtils.isEmpty(progreso) || progreso.getIdProgreso() == 0) {
            return new ResponseEntity<>(progreso, HttpStatus.BAD_REQUEST);
        }

        progreso = service.actualizarProgreso(progreso);

        if (ObjectUtils.isEmpty(progreso)) {
            return new ResponseEntity<>(progreso, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(progreso, HttpStatus.OK);
    }
    @Operation(
            tags = {"Progresos"},
            summary = "Permite obtener un progreso por ID",
            description = "Busca un progreso específica mediante su ID"
    )
    @GetMapping("/buscarProgreso")
    public ResponseEntity<ProgresoLecciones> buscarProgreso(@RequestParam int id) {

        if (id <= 0) {
            return new ResponseEntity<>(new ProgresoLecciones(), HttpStatus.BAD_REQUEST);
        }

        ProgresoLecciones progreso = service.buscarPorIdProgreso(id);

        if (ObjectUtils.isEmpty(progreso)) {
            return new ResponseEntity<>(new ProgresoLecciones(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(progreso, HttpStatus.OK);
    }
    @Operation(
            tags = {"Progresos"},
            summary = "Permite eliminar un progreso",
            description = "Elimina físicamente un progreso de la base de datos por su ID"
    )
    @DeleteMapping("/eliminarProgreso")
    public ResponseEntity<Boolean> eliminarProgreso(@RequestParam int id) {

        if (id <= 0) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.eliminarProgreso(id), HttpStatus.OK);
    }
}