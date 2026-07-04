package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Leccion;

import java.util.List;

@Repository
public interface ILeccionRepository extends JpaRepository<Leccion, Integer> {
    public List<Leccion> findByModulo_IdModulo(int idModulo);
}

