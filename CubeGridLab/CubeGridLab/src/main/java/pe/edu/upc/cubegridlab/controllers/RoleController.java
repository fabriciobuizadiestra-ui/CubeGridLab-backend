package pe.edu.upc.cubegridlab.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.cubegridlab.dtos.QuantityUsersByRoleDTO;
import pe.edu.upc.cubegridlab.dtos.RoleDTO;
import pe.edu.upc.cubegridlab.servicesinterfaces.IRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRoleService rS;

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
}

