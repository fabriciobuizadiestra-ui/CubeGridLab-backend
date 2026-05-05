package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.entities.Evaluaciones;
import pe.edu.upc.cubegridlab.servicesinterfaces.IEvaluacionesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionesController {

    @Autowired
    private IEvaluacionesService eS;

    @GetMapping
    public List<Evaluaciones> listar() {
        return eS.list();
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Evaluaciones e) {
        return ResponseEntity.ok(eS.insert(e));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Evaluaciones> evaluacion = eS.listId(id);

        if (evaluacion.isPresent()) {
            return ResponseEntity.ok(evaluacion.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        eS.delete(id);
        return ResponseEntity.ok("Evaluación eliminada correctamente");
    }
}
