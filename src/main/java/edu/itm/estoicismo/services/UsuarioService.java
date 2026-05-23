package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesSQL.Usuarios;
import edu.itm.estoicismo.repositoriesSQL.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) { this.repository = repository; }

    public List<Usuarios> buscarUsuarios() { return repository.buscarUsuarios(); }

    public Usuarios login(String email, String password) {
        Usuarios user = repository.buscarUsuarioPorEmail(email);

        // Si el usuario existe y la contraseña coincide (aquí comparas plano o con BCrypt según uses)
        if (user != null && user.getPasswordHash().equals(password)) {
            return user; // Credenciales correctas
        }
        return null; // Credenciales incorrectas o usuario no encontrado
    }

    public Usuarios crearUsuario(Usuarios user) {
        user.setIdUsuario(repository.generarNuevoIdUsuario());
        return repository.insertarUsuario(user);
    }
    public Usuarios actualizarUsuario(Usuarios user) {
        return repository.actualizarUsuario(user);
    }

    public boolean eliminarUsuario(int idUser) {
        return repository.eliminarUsuario(idUser);
    }

    public Usuarios buscarUsuarioId(int id) {
        return repository.BuscarUsuarioId(id);
    }
}
