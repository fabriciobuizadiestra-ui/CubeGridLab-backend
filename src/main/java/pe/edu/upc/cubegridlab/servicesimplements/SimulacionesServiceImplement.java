package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Simulaciones;
import pe.edu.upc.cubegridlab.repositories.ISimulacionesRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISimulacionesService;

import java.util.List;
import java.util.Optional;

@Service
public class SimulacionesServiceImplement implements ISimulacionesService {

    @Autowired
    private ISimulacionesRepository sR;


    @Override
    public List<Simulaciones> list() {
        return sR.findAll();
    }

    @Override
    public Simulaciones insert(Simulaciones s) {
        return sR.save(s);
    }

    @Override
    public Optional<Simulaciones> listId(int id) {
        return sR.findById(id);
    }

    @Override
    public void update(Simulaciones s) {
        sR.save(s);
    }

    @Override
    public void delete(int id) {
        sR.deleteById(id);
    }
}
