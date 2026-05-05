package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Misiones;

@Repository
public interface IMisionesRepository extends JpaRepository<Misiones, Integer> {
    @Query("SELECT COUNT(DISTINCT m) FROM Simulaciones s JOIN s.mision m WHERE TRIM(UPPER(s.estado)) = 'COMPLETADA'")
    Long contarMisionesExitosas();
}
