package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.ResultadoEvaluacion;

import java.util.List;
import java.util.Optional;

public interface IResultadoEvaluacionesService {
    public List<ResultadoEvaluacion> list();
    ResultadoEvaluacion insert(ResultadoEvaluacion r);
    Optional<ResultadoEvaluacion> listId(int id);
    void update(ResultadoEvaluacion r);
    void delete(int id);
    List<Object[]> quantityResultsByUser();
}
