package pe.edu.upc.cubegridlab.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Misiones;
import pe.edu.upc.cubegridlab.repositories.IMisionesRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IMisionesService;

import java.util.List;
import java.util.Optional;

@Service
public class MisionesImplement implements IMisionesService {
    @Autowired
    private IMisionesRepository mR;

    @Override
    public List<Misiones> list() {return mR.findAll();}

    @Override
    public Misiones insert(Misiones m) {
        return mR.save(m);
    }

    @Override
    public Optional<Misiones> listId(int id) {
        return mR.findById(id);
    }

    @Override
    public void update(Misiones m) {
        mR.save(m);
    }

    @Override
    public void delete(int id) {
        mR.deleteById(id);
    }

    @Override
    public Long contarMisionesExitosas() {
        return mR.contarMisionesExitosas();
    }
}
