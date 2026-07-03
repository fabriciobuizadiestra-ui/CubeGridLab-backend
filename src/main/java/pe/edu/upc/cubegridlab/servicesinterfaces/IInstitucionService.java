package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.Institucion;

import java.util.List;
import java.util.Optional;

public interface IInstitucionService {
    public List<Institucion> list();
    public Institucion insert(Institucion institucion);
    public Optional<Institucion> listId(int id);
    public void update(Institucion institucion);
    public void delete(int id);
}
