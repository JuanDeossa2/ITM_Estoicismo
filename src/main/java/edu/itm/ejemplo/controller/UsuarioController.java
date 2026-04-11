package edu.itm.ejemplo.controller;

import edu.itm.ejemplo.entitiesSQL.Usuarios;
import edu.itm.ejemplo.services.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(
            tags = {"Usuarios"},
            summary = "Permite obtener la lista de usuarios",
            description = "Obtiene todos los usuarios desde la base de datos",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Lista de usuarios",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Usuarios.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping("/listaUsuarios")
    public ResponseEntity<List<Usuarios>> listarUsuario(){
        return new ResponseEntity<>(service.buscarUsuarios(), HttpStatus.OK);
    }

    @Operation(
            tags = {"Usuarios"},
            summary = "Permite insertar un nuevo usuario",
            description = "Inserta un nuevo usuario en la base de datos"
    )
    @PostMapping("/nuevoUsuario")
    public ResponseEntity<Usuarios> insertarUsuario(@RequestBody Usuarios user){

        if(ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(user.getNombreCompleto()) || ObjectUtils.isEmpty(user.getEmail())){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }

        user = service.crearUsuario(user);

        if(ObjectUtils.isEmpty(user)){
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Operation(
            tags = {"Usuarios"},
            summary = "Permite actualizar un usuario",
            description = "Actualiza la información de un usuario existente"
    )
    @PutMapping("/actualizarUsuario")
    public ResponseEntity<Usuarios> actualizarUsuario(@RequestBody Usuarios user){

        if(ObjectUtils.isEmpty(user) ||
                ObjectUtils.isEmpty(user.getIdUsuario()) ||
                user.getIdUsuario() == 0){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }

        user = service.actualizarUsuario(user);

        if(ObjectUtils.isEmpty(user)){
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            tags = {"Usuarios"},
            summary = "Permite obtener un usuario por ID",
            description = "Busca un usuario específico mediante su ID"
    )
    @GetMapping("/buscarUsuarioPorId")
    public ResponseEntity<Usuarios> buscarUsuarioId(@RequestParam int id){

        if(id <= 0){
            return new ResponseEntity<>(new Usuarios(), HttpStatus.BAD_REQUEST);
        }

        Usuarios user = service.buscarUsuarioId(id);

        if(ObjectUtils.isEmpty(user)){
            return new ResponseEntity<>(new Usuarios(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            tags = {"Usuarios"},
            summary = "Permite eliminar un usuario",
            description = "Elimina físicamente un usuario de la base de datos por su ID"
    )
    @DeleteMapping("/eliminarUsuario")
    public ResponseEntity<Boolean> eliminarUsuario(@RequestParam int id){

        if(id <= 0){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        boolean eliminado = service.eliminarUsuario(id);
        return new ResponseEntity<>(eliminado, HttpStatus.OK);
    }
}