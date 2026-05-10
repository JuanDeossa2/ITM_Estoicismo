package edu.itm.estoicismo.controller;

import edu.itm.estoicismo.entitiesJPA.RetoDiario;
import edu.itm.estoicismo.entitiesJPA.RegistroReto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/retos")
public interface RetoAPI {
    // --- CRUD RETOS DIARIOS ---
    @Tag(name = "Reto Diario")
    @Operation(summary = "Listar todos los retos")
    @GetMapping("/ListaRetos")
    ResponseEntity<List<RetoDiario>> getRetos();

    @Tag(name = "Reto Diario")
    @Operation(summary = "Buscar reto por ID")
    @GetMapping("/buscarReto/{id}")
    ResponseEntity<RetoDiario> getRetoById(@PathVariable Integer id);

    @Tag(name = "Reto Diario")
    @Operation(summary = "Crear/Guardar un nuevo reto")
    @PostMapping("/CrarReto")
    ResponseEntity<RetoDiario> guardarReto(@RequestBody RetoDiario reto);

    @Tag(name = "Reto Diario")
    @Operation(summary = "Actualizar un reto existente")
    @PutMapping("/actualizarReto")
    ResponseEntity<RetoDiario> actualizarReto(@RequestBody RetoDiario reto);

    @Tag(name = "Reto Diario")
    @Operation(summary = "Eliminar un reto")
    @DeleteMapping("/eliminarReto/{id}")
    ResponseEntity<Void> eliminarReto(@PathVariable Integer id);

    // --- CRUD REGISTRO DE RETOS ---
    @Tag(name = "Registro Reto")
    @Operation(summary = "Registrar cumplimiento")
    @PostMapping("/registroRetos/registrar")
    ResponseEntity<RegistroReto> postRegistro(@RequestBody RegistroReto registro);

    @Tag(name = "Registro Reto")
    @Operation(summary = "Actualizar un registro de cumplimiento")
    @PutMapping("/registroRetos/actualizar")
    ResponseEntity<RegistroReto> putRegistro(@RequestBody RegistroReto registro);

    @Tag(name = "Registro Reto")
    @Operation(summary = "Ver progreso de un usuario")
    @GetMapping("/registroRetos/usuario/{id}")
    ResponseEntity<List<RegistroReto>> getProgresoUsuario(@PathVariable Integer id);

    @Tag(name = "Registro Reto")
    @Operation(summary = "Eliminar un registro de cumplimiento")
    @DeleteMapping("/registroRetos/EliminarRegistro/{id}")
    ResponseEntity<Void> eliminarRegistro(@PathVariable Integer id);
}