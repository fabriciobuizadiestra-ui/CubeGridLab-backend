package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.entities.Misiones;
import pe.edu.upc.cubegridlab.servicesinterfaces.IMisionesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/misiones")
public class MisionesController {

    @Autowired
    private IMisionesService mS;

    @GetMapping
    public List<Misiones> listar() {
        return mS.list();
    }

    @GetMapping("/cantidad-exitosas")
    public Long contarMisionesExitosas() {
        return mS.contarMisionesExitosas();
    }
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Misiones m) {
        return ResponseEntity.ok(mS.insert(m));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Misiones> mision = mS.listId(id);

        if (mision.isPresent()) {
            return ResponseEntity.ok(mision.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        mS.delete(id);
        return ResponseEntity.ok("Misión eliminada correctamente");
    }

    @PostMapping("/{id}/clonar") //US20: Clonar una misión
    public ResponseEntity<?> clonarMision(@PathVariable int id) {

        Optional<Misiones> original = mS.listId(id);

        if (original.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Misiones nueva = new Misiones();

        nueva.setNombreMision(
                original.get().getNombreMision() + " - copia"
        );

        nueva.setDescripcionMision(
                original.get().getDescripcionMision()
        );

        nueva.setUsuario(
                original.get().getUsuario()
        );

        nueva.setCubesat(
                original.get().getCubesat()
        );

        return ResponseEntity.ok(
                mS.insert(nueva)
        );
    }
}
