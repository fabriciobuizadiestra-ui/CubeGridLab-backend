package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Curso;
import java.util.List;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Integer> {
	// Buscar cursos por id del docente
	List<Curso> findByDocente_IdUser(int idUser);

	// Reporte: cantidad de modulos por curso (incluye cursos sin modulos)
	@Query("SELECT c.idCurso, c.nombre, COUNT(m) FROM Curso c LEFT JOIN c.modulos m GROUP BY c.idCurso, c.nombre")
	List<Object[]> cantidadModulosPorCurso();
}
