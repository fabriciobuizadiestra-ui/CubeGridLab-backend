package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Institucion;
import pe.edu.upc.cubegridlab.repositories.IInstitucionRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IInstitucionService;

import java.util.List;
import java.util.Optional;

@Service
public class InstitucionServiceImplement implements IInstitucionService {
    @Autowired
    private IInstitucionRepository iR;

    @Override
    public List<Institucion> list() {
        return iR.findAll();
    }

    @Override
    public Institucion insert(Institucion institucion) {
        return iR.save(institucion);
    }

    @Override
    public Optional<Institucion> listId(int id) {
        return iR.findById(id);
    }

    @Override
    public void update(Institucion institucion) {
        iR.save(institucion);
    }

    @Override
    public void delete(int id) {
        iR.deleteById(id);
    }
}
