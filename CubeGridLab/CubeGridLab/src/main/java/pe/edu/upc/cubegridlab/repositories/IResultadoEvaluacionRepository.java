package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.ResultadoEvaluacion;

import java.util.List;

@Repository
public interface IResultadoEvaluacionRepository extends JpaRepository<ResultadoEvaluacion, Integer> {
    // Reporte: Cantidad de resultados de evaluación por usuario
    @Query("SELECT u.nameUser, COUNT(r) FROM ResultadoEvaluacion r JOIN r.user u GROUP BY u.nameUser")
    List<Object[]> quantityResultsByUser();
}
