package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.entities.Cubesat;
import pe.edu.upc.cubegridlab.servicesinterfaces.ICubesatService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cubesats")
public class CubesatController {

    @Autowired
    private ICubesatService cS;

    @GetMapping
    public List<Cubesat> listar() {
        return cS.list();
    }

    @GetMapping("/total-utilizados-simulaciones")
    public Long contarCubesatsUtilizadosEnSimulaciones() {
        return cS.contarCubesatsUtilizadosEnSimulaciones();
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Cubesat c) {
        return ResponseEntity.ok(cS.insert(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Cubesat> cubesat = cS.listId(id);

        if (cubesat.isPresent()) {
            return ResponseEntity.ok(cubesat.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Cubesat c) {
        cS.update(c);
        return ResponseEntity.ok("CubeSat actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        cS.delete(id);
        return ResponseEntity.ok("CubeSat eliminado correctamente");
    }
}
