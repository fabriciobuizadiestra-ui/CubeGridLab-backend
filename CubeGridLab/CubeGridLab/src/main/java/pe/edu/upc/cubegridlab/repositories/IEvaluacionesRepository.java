package pe.edu.upc.cubegridlab.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Evaluaciones;

@Repository
public interface IEvaluacionesRepository extends JpaRepository<Evaluaciones, Integer> {
}
