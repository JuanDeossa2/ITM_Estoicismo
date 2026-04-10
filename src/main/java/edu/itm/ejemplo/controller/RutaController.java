package edu.itm.ejemplo.controller;

import edu.itm.ejemplo.entitiesSQL.RutaAprendizaje;
import edu.itm.ejemplo.services.RutaService;
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
@RequestMapping("/rutas-estoicas")
public class RutaController {

    @Autowired
    private RutaService service;

    @Operation(
            tags = {"Rutas"},
            summary = "Permite obtener la lista de rutas",
            description = "Obtiene todas las rutas desde la base de datos",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Lista de rutas",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = RutaAprendizaje.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping("/listar")
    public ResponseEntity<List<RutaAprendizaje>> getRutas(){
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @Operation(
            tags = {"Rutas"},
            summary = "Permite insertar una nueva ruta",
            description = "Inserta una nueva ruta en la base de datos"
    )
    @PostMapping("/nuevo")
    public ResponseEntity<RutaAprendizaje> insertarRuta(@RequestBody RutaAprendizaje ruta){

        if(ObjectUtils.isEmpty(ruta) || ObjectUtils.isEmpty(ruta.getNombreRuta())){
            return new ResponseEntity<>(ruta, HttpStatus.BAD_REQUEST);
        }

        ruta = service.crear(ruta);

        if(ObjectUtils.isEmpty(ruta)){
            return new ResponseEntity<>(ruta, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(ruta, HttpStatus.CREATED);
    }

    @Operation(
            tags = {"Rutas"},
            summary = "Permite actualizar una ruta",
            description = "Actualiza una ruta existente"
    )
    @PutMapping("/actualizar")
    public ResponseEntity<RutaAprendizaje> actualizarRuta(@RequestBody RutaAprendizaje ruta){

        if(ObjectUtils.isEmpty(ruta) ||
                ObjectUtils.isEmpty(ruta.getNombreRuta()) ||
                ObjectUtils.isEmpty(ruta.getIdRuta())){
            return new ResponseEntity<>(ruta, HttpStatus.BAD_REQUEST);
        }

        ruta = service.actualizar(ruta);

        if(ObjectUtils.isEmpty(ruta)){
            return new ResponseEntity<>(ruta, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(ruta, HttpStatus.CREATED);
    }

    @Operation(
            tags = {"Rutas"},
            summary = "Permite obtener una ruta por ID",
            description = "Devuelve una ruta específica"
    )
    @GetMapping("/buscarPorId")
    public ResponseEntity<RutaAprendizaje> getRuta(@RequestParam int id){

        if(ObjectUtils.isEmpty(id) || id == 0){
            return new ResponseEntity<>(new RutaAprendizaje(), HttpStatus.BAD_REQUEST);
        }

        RutaAprendizaje ruta = service.getRuta(id);

        if(ObjectUtils.isEmpty(ruta)){
            return new ResponseEntity<>(new RutaAprendizaje(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ruta, HttpStatus.OK);
    }

    @Operation(
            tags = {"Rutas"},
            summary = "Permite eliminar una ruta",
            description = "Elimina una ruta por ID"
    )
    @DeleteMapping("/eliminar")
    public ResponseEntity<Boolean> eliminarRuta(@RequestParam int id){

        if(ObjectUtils.isEmpty(id) || id == 0){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }
}