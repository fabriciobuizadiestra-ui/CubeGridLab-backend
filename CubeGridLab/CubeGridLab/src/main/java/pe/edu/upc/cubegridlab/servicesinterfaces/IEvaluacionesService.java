package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.Evaluaciones;
import pe.edu.upc.cubegridlab.entities.Misiones;

import java.util.List;
import java.util.Optional;

public interface IEvaluacionesService {
    public List<Evaluaciones> list();
    Evaluaciones insert(Evaluaciones e);
    Optional<Evaluaciones> listId(int id);
    void update(Evaluaciones e);
    void delete(int id);
}
