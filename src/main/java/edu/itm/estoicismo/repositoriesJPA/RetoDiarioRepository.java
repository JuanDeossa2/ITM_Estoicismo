package edu.itm.estoicismo.repositoriesJPA;

import edu.itm.estoicismo.entitiesJPA.RetoDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetoDiarioRepository extends JpaRepository<RetoDiario, Integer> { }