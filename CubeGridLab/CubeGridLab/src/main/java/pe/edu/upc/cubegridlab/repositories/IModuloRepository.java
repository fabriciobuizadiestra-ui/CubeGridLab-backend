package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Modulo;

import java.util.List;

@Repository
public interface IModuloRepository extends JpaRepository<Modulo, Integer> {
    public List<Modulo> findByCurso_IdCurso(int idCurso);
}

