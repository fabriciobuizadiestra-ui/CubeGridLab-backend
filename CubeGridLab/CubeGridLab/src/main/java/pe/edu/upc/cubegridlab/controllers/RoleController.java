package pe.edu.upc.cubegridlab.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.cubegridlab.dtos.QuantityUsersByRoleDTO;
import pe.edu.upc.cubegridlab.dtos.RoleDTO;
import pe.edu.upc.cubegridlab.servicesinterfaces.IRoleService;
import pe.edu.upc.cubegridlab.entities.Role;
import pe.edu.upc.cubegridlab.repositories.IRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRoleService rS;

    @Autowired
    private IRoleRepository rR;

    @GetMapping("listar")
    public ResponseEntity<List<RoleDTO>> listar() {
        ModelMapper m = new ModelMapper();
        List<RoleDTO> listaRoles = rS.list().stream()
                .map(y -> m.map(y, RoleDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaRoles);
    }

    @GetMapping("cantidad-de-usuarios")
    public ResponseEntity<?>quantityUserByRole(){
        List<Object[]>listaRoles=rS.quantityUserByRole();

        if(listaRoles.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay registros");
        }
        List<QuantityUsersByRoleDTO> respuesta=new ArrayList<>();
        for(Object[] fila:listaRoles){
            QuantityUsersByRoleDTO dto=new QuantityUsersByRoleDTO();
            dto.setNameRole((String)fila[0]);
            dto.setQuantityUsers(((Number)fila[1]).intValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody RoleDTO dto) {
        if (dto == null || dto.getNameRole() == null || dto.getNameRole().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre de rol es obligatorio");
        }
        Role role = new Role();
        role.setNameRole(dto.getNameRole());
        Role saved = rR.save(role);
        RoleDTO response = new RoleDTO();
        response.setIdRole(saved.getIdRole());
        response.setNameRole(saved.getNameRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<?> actualizar(@RequestBody RoleDTO dto) {
        if (dto == null || dto.getIdRole() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de rol inválido");
        }
        var opt = rR.findById(dto.getIdRole());
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
        Role role = opt.get();
        if (dto.getNameRole() != null && !dto.getNameRole().isBlank()) role.setNameRole(dto.getNameRole());
        rR.save(role);
        RoleDTO resp = new RoleDTO();
        resp.setIdRole(role.getIdRole());
        resp.setNameRole(role.getNameRole());
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (id <= 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID inválido");
        var opt = rR.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        rR.deleteById(id);
        return ResponseEntity.ok("Rol eliminado correctamente");
    }
}

