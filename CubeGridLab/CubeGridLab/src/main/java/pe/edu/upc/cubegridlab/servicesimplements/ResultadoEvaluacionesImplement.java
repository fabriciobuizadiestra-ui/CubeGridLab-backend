package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.ResultadoEvaluacion;
import pe.edu.upc.cubegridlab.repositories.IResultadoEvaluacionRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IResultadoEvaluacionesService;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoEvaluacionesImplement implements IResultadoEvaluacionesService {
    @Autowired
    private IResultadoEvaluacionRepository reR;
    @Override
    public List<ResultadoEvaluacion> list() {return reR.findAll();}

    @Override
    public ResultadoEvaluacion insert(ResultadoEvaluacion r) {
        return reR.save(r);
    }

    @Override
    public Optional<ResultadoEvaluacion> listId(int id) {
        return reR.findById(id);
    }

    @Override
    public void update(ResultadoEvaluacion r) {
        reR.save(r);
    }

    @Override
    public void delete(int id) {
        reR.deleteById(id);
    }

    @Override
    public List<Object[]> quantityResultsByUser() {
        return reR.quantityResultsByUser();
    }
}
