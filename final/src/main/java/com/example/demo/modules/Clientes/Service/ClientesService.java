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

    private ClientesDTO convertiraDTO(Cliente ent){
        ClientesDTO dto = new ClientesDTO();
        dto.setId_cliente(ent.getId_cliente());
        dto.setNombre(ent.getNombre());
        dto.setApellido(ent.getApellido());
        dto.setTelefono(ent.getTelefono());
        dto.setEmail(ent.getEmail());
        dto.setDireccion(ent.getDireccion());
        return dto;
    }

    //POST
    public ClientesDTO crearCliente(ClientesDTO dto){
        Cliente ent = convertiraEntity(dto);
        Cliente nuevoCliente = repo.save(ent);
        return convertiraDTO(nuevoCliente);
    }

    //GET
    public List<ClientesDTO> listarClientes(){
        List<Cliente> list = repo.findAll();
        List<ClientesDTO> listDTO = new ArrayList<>();
        for(Cliente ent : list){
            listDTO.add(convertiraDTO(ent));
        }
        return listDTO;
    }

    //GETBYID
    public ClientesDTO obtenerCliente(Long id){
        Cliente ent = repo.findById(id).orElseThrow(()-> new RuntimeException("Cliente no encontrado con id " + id));
        return convertiraDTO(ent);
    }

    //PUT
    public ClientesDTO modificarCliente(Long id, ClientesDTO dto){
        Cliente ent = repo.findById(id).orElseThrow(()-> new RuntimeException("Cliente no encontrado con id " + id));
        ent.setNombre(dto.getNombre());
        ent.setApellido(dto.getApellido());
        ent.setTelefono(dto.getTelefono());
        ent.setEmail(dto.getEmail());
        ent.setDireccion(dto.getDireccion());
        Cliente actualizado = repo.save(ent);
        return convertiraDTO(actualizado);
    }

    //DELETE
    public void eliminarCliente(Long id){
        Cliente ent = repo.findById(id).orElseThrow();
        if(ent!=null){
            repo.delete(ent);
        }
    }
}
