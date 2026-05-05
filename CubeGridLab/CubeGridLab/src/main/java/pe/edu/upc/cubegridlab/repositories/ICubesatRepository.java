package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.cubegridlab.entities.Cubesat;

public interface ICubesatRepository extends JpaRepository<Cubesat, Integer> {
    @Query("SELECT COUNT(DISTINCT c) FROM Simulaciones s JOIN s.mision m JOIN m.cubesat c")
    Long contarCubesatsUtilizadosEnSimulaciones();
}
