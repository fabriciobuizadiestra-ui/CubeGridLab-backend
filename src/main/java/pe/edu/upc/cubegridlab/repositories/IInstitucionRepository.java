package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Institucion;

@Repository
public interface IInstitucionRepository extends JpaRepository<Institucion, Integer> {
}
