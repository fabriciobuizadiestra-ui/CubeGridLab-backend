package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Curso;
import java.util.List;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Integer> {
	// Buscar cursos por id del docente
	List<Curso> findByDocente_IdUser(int idUser);
}

