package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.SimulacionInsertDTO;
import pe.edu.upc.cubegridlab.dtos.SimulacionResponseDTO;
import pe.edu.upc.cubegridlab.entities.Misiones;
import pe.edu.upc.cubegridlab.entities.Simulaciones;
import pe.edu.upc.cubegridlab.servicesinterfaces.IMisionesService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISimulacionesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/simulaciones")
public class SimulacionesController {

    @Autowired
    private ISimulacionesService sS;

    @Autowired
    private IMisionesService mS;

    @GetMapping("/Listar")
    public List<SimulacionResponseDTO> listar() {
        return sS.list()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/iniciar") //US16: Ejecutar simulación de misión
    public ResponseEntity<?> iniciarSimulacion(@RequestBody SimulacionInsertDTO dto) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().isBlank()
                || dto.getIdMision() == null || dto.getIdMision() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nombre e idMision son obligatorios");
        }

        Optional<Misiones> mision = mS.listId(dto.getIdMision());
        if (mision.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Mision no encontrada");
        }

        Simulaciones s = new Simulaciones();
        s.setNombre(dto.getNombre());
        s.setFecha_inicio(dto.getFecha_inicio());
        s.setFecha_fin(dto.getFecha_fin());
        s.setTipo(dto.getTipo());
        s.setAltitud(dto.getAltitud());
        s.setInclinacion(dto.getInclinacion());
        s.setVelocidad(dto.getVelocidad());
        s.setMision(mision.get());
        s.setEstado("COMPLETADA");
        s.setResultado("Órbita estable alcanzada correctamente");

        return ResponseEntity.ok(toResponseDTO(sS.insert(s)));
    }

    @GetMapping("/{id}/Buscar")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Simulaciones> simulacion = sS.listId(id);

        if (simulacion.isPresent()) {
            return ResponseEntity.ok(toResponseDTO(simulacion.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/Eliminar")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        sS.delete(id);
        return ResponseEntity.ok("Simulación eliminada");
    }

    @GetMapping("/historial") //US02: Como usuario, quiero revisar mis simulaciones anteriores
    public ResponseEntity<?> historialSimulaciones() {
        List<SimulacionResponseDTO> historial = sS.list()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/{id}/resultado") //US05: Como usuario, quiero ver si una simulación falló
    public ResponseEntity<?> verResultado(@PathVariable int id) {

        Optional<Simulaciones> simulacion = sS.listId(id);

        if (simulacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(simulacion.get().getResultado());
    }

    @GetMapping("/{id}/informe") //US18: Como usuario, quiero obtener informe posterior a la misión
    public ResponseEntity<?> informeMision(@PathVariable int id) {

        Optional<Simulaciones> simulacion = sS.listId(id);

        if (simulacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Simulaciones s = simulacion.get();

        String informe = "Simulación: " + s.getNombre()
                + " | Estado: " + s.getEstado()
                + " | Resultado: " + s.getResultado()
                + " | Tipo: " + s.getTipo();

        return ResponseEntity.ok(informe);
    }

    @GetMapping("/{id}/reporte") //US03: Como estudiante, quiero generar un reporte PDF
    public ResponseEntity<?> generarReporte(@PathVariable int id) {

        Optional<Simulaciones> simulacion = sS.listId(id);

        if (simulacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Simulaciones s = simulacion.get();

        String reporte = "=== REPORTE DE SIMULACIÓN ===\n"
                + "Nombre: " + s.getNombre() + "\n"
                + "Estado: " + s.getEstado() + "\n"
                + "Resultado: " + s.getResultado() + "\n"
                + "Altitud: " + s.getAltitud() + "\n"
                + "Velocidad: " + s.getVelocidad();

        return ResponseEntity.ok(reporte);
    }

    private SimulacionResponseDTO toResponseDTO(Simulaciones simulacion) {
        SimulacionResponseDTO dto = new SimulacionResponseDTO();
        dto.setIdSimulacion(simulacion.getIdSimulacion());
        dto.setNombre(simulacion.getNombre());
        dto.setFecha_inicio(simulacion.getFecha_inicio());
        dto.setFecha_fin(simulacion.getFecha_fin());
        dto.setTipo(simulacion.getTipo());
        dto.setAltitud(simulacion.getAltitud());
        dto.setInclinacion(simulacion.getInclinacion());
        dto.setVelocidad(simulacion.getVelocidad());
        dto.setEstado(simulacion.getEstado());
        dto.setResultado(simulacion.getResultado());

        if (simulacion.getMision() != null) {
            dto.setIdMision(simulacion.getMision().getIdMision());
            dto.setNombreMision(simulacion.getMision().getNombreMision());

            if (simulacion.getMision().getUsuario() != null) {
                dto.setIdUsuario(simulacion.getMision().getUsuario().getIdUser());
            }
            if (simulacion.getMision().getCubesat() != null) {
                dto.setIdCubesat(simulacion.getMision().getCubesat().getIdCubesat());
            }
        }

        return dto;
    }

}
