package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Modulo;

import java.util.List;

@Repository
public interface IModuloRepository extends JpaRepository<Modulo, Integer> {
    public List<Modulo> findByCurso_IdCurso(int idCurso);

    // Reporte: cantidad de lecciones por modulo (incluye modulos sin lecciones)
    @Query("SELECT m.idModulo, m.nombre, COUNT(l) FROM Modulo m LEFT JOIN m.lecciones l GROUP BY m.idModulo, m.nombre")
    List<Object[]> cantidadLeccionesPorModulo();
}

