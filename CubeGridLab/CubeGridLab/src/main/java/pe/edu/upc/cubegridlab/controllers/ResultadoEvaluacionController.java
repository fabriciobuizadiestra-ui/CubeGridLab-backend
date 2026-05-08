package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.entities.ResultadoEvaluacion;
import pe.edu.upc.cubegridlab.servicesinterfaces.IResultadoEvaluacionesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resultado-evaluacion")
public class ResultadoEvaluacionController {

    @Autowired
    private IResultadoEvaluacionesService rS;

    @GetMapping
    public List<ResultadoEvaluacion> listar() {
        return rS.list();
    }

    @GetMapping("/reporte-resultados-usuario")
    public List<Object[]> reporteResultadosPorUsuario() {
        return rS.quantityResultsByUser();
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody ResultadoEvaluacion r) {
        return ResponseEntity.ok(rS.insert(r));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<ResultadoEvaluacion> resultado = rS.listId(id);

        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        rS.delete(id);
        return ResponseEntity.ok("Resultado eliminado correctamente");
    }
}