package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Cubesat;
import pe.edu.upc.cubegridlab.repositories.ICubesatRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.ICubesatService;

import java.util.List;
import java.util.Optional;

@Service
public class CubesatServiceImplement implements ICubesatService {

    @Autowired
    private ICubesatRepository cR;

    @Override
    public List<Cubesat> list() {
        return cR.findAll();
    }

    @Override
    public Cubesat insert(Cubesat c) {
        return cR.save(c);
    }

    @Override
    public Optional<Cubesat> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Cubesat c) {
        cR.save(c);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public Long contarCubesatsUtilizadosEnSimulaciones() {
        return cR.contarCubesatsUtilizadosEnSimulaciones();
    }
}
