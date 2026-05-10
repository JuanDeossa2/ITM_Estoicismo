package edu.itm.estoicismo.controller;

import edu.itm.estoicismo.entitiesJPA.RetoDiario;
import edu.itm.estoicismo.entitiesJPA.RegistroReto;
import edu.itm.estoicismo.services.RetoServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RetoController implements RetoAPI {
    private final RetoServiceInterface service;
    public RetoController(RetoServiceInterface service) {
        this.service = service;
    }

    // Retos
    @Override
    public ResponseEntity<List<RetoDiario>> getRetos() {
        return new ResponseEntity<>(service.listarRetos(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RetoDiario> getRetoById(Integer id) {
        return service.buscarRetoPorId(id)
                .map(reto -> new ResponseEntity<>(reto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<RetoDiario> guardarReto(RetoDiario reto) {
        return new ResponseEntity<>(service.guardarReto(reto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RetoDiario> actualizarReto(RetoDiario reto) {
        return new ResponseEntity<>(service.actualizarReto(reto), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> eliminarReto(Integer id) {
        service.eliminarReto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Registros
    @Override
    public ResponseEntity<RegistroReto> postRegistro(RegistroReto registro) {
        return new ResponseEntity<>(service.registrarCumplimiento(registro), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RegistroReto> putRegistro(RegistroReto registro) {
        return new ResponseEntity<>(service.actualizarRegistro(registro), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<RegistroReto>> getProgresoUsuario(Integer id) {
        return new ResponseEntity<>(service.obtenerHistorialUsuario(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> eliminarRegistro(Integer id) {
        service.eliminarRegistro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}