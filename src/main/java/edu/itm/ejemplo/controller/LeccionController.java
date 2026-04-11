package edu.itm.ejemplo.controller;

import edu.itm.ejemplo.entitiesSQL.Lecciones;
import edu.itm.ejemplo.entitiesSQL.Usuarios;
import edu.itm.ejemplo.services.LeccionService;
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
@RequestMapping("/lecciones")
public class LeccionController {

    @Autowired
    private LeccionService service;

    @Operation(
            tags = {"Lecciones"},
            summary = "Permite obtener la lista de lecciones",
            description = "Obtiene todos las lecciones desde la base de datos",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Lista de lecciones",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Lecciones.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping("/listaLecciones")
    public ResponseEntity<List<Lecciones>> listar() {
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }
    @Operation(
            tags = {"Lecciones"},
            summary = "Permite insertar una nueva leccion",
            description = "Inserta una nueva leccion en la base de datos"
    )
    @PostMapping("/nuevoLeccion")
    public ResponseEntity<Lecciones> crearLeccion(@RequestBody Lecciones leccion) {

        if (ObjectUtils.isEmpty(leccion) || ObjectUtils.isEmpty(leccion.getTitulo())) {
            return new ResponseEntity<>(leccion, HttpStatus.BAD_REQUEST);
        }

        leccion = service.crearLeccion(leccion);

        if (ObjectUtils.isEmpty(leccion)) {
            return new ResponseEntity<>(leccion, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(leccion, HttpStatus.CREATED);
    }
    @Operation(
            tags = {"Lecciones"},
            summary = "Permite actualizar una leccion",
            description = "Actualiza la información de una leccion existente"
    )
    @PutMapping("/actualizarLeccion")
    public ResponseEntity<Lecciones> actualizarLeccion(@RequestBody Lecciones leccion) {

        if (ObjectUtils.isEmpty(leccion) || leccion.getIdLeccion() == 0) {
            return new ResponseEntity<>(leccion, HttpStatus.BAD_REQUEST);
        }

        leccion = service.actualizarLeccion(leccion);

        if (ObjectUtils.isEmpty(leccion)) {
            return new ResponseEntity<>(leccion, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(leccion, HttpStatus.OK);
    }
    @Operation(
            tags = {"Lecciones"},
            summary = "Permite obtener una leccion por ID",
            description = "Busca una leccion específica mediante su ID"
    )
    @GetMapping("/buscarLeccion")
    public ResponseEntity<Lecciones> buscarLeccionPorId(@RequestParam int id) {

        if (id <= 0) {
            return new ResponseEntity<>(new Lecciones(), HttpStatus.BAD_REQUEST);
        }

        Lecciones leccion = service.buscarLeccionPorId(id);

        if (ObjectUtils.isEmpty(leccion)) {
            return new ResponseEntity<>(new Lecciones(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(leccion, HttpStatus.OK);
    }
    @Operation(
            tags = {"Lecciones"},
            summary = "Permite eliminar una leccion",
            description = "Elimina físicamente una leccion de la base de datos por su ID"
    )
    @DeleteMapping("/eliminarLeccion")
    public ResponseEntity<Boolean> eliminar(@RequestParam int id) {

        if (id <= 0) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.eliminarLeccion(id), HttpStatus.OK);
    }
}