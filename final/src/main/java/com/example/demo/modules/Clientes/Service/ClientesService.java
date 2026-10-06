package com.example.demo.modules.Clientes.Service;

import com.example.demo.modules.Clientes.Repository.ClientesRepository;
import com.example.demo.modules.Clientes.model.DTO.ClientesDTO;
import com.example.demo.modules.Clientes.model.Entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ClientesService {
    private final ClientesRepository repo;

    public ClientesService(ClientesRepository repo) {
        this.repo = repo;
    }

    private Cliente convertiraEntity(ClientesDTO dto){
        Cliente ent = new Cliente();
        ent.setNombre(dto.getNombre());
        ent.setApellido(dto.getApellido());
        ent.setTelefono(dto.getTelefono());
        ent.setEmail(dto.getEmail());
        ent.setDireccion(dto.getDireccion());
        return ent;
    }
}
