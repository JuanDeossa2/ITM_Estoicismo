package edu.itm.estoicismo.repositoriesJPA;

import edu.itm.estoicismo.entitiesJPA.RegistroReto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegistroRetoRepository extends JpaRepository<RegistroReto, Integer> {
    List<RegistroReto> findByUsuarioIdUsuario(Integer idUsuario);
}
