package pe.edu.upc.cubegridlab.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Evaluaciones;
import pe.edu.upc.cubegridlab.repositories.IEvaluacionesRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IEvaluacionesService;


import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionesImplement implements IEvaluacionesService {
    @Autowired
    private IEvaluacionesRepository evR;

    @Override
    public List<Evaluaciones> list() {return evR.findAll();}

    @Override
    public Evaluaciones insert(Evaluaciones e) {
        return evR.save(e);
    }

    @Override
    public Optional<Evaluaciones> listId(int id) {
        return evR.findById(id);
    }

    @Override
    public void update(Evaluaciones e) {
        evR.save(e);
    }

    @Override
    public void delete(int id) {
        evR.deleteById(id);
    }
}
