package com.example.demo.modules.Eventos.Service;

import com.example.demo.modules.Clientes.Repository.ClientesRepository;
import com.example.demo.modules.Clientes.model.Entity.Cliente;
import com.example.demo.modules.Eventos.Repository.EventosRepository;
import com.example.demo.modules.Eventos.model.DTO.EventosDTO;
import com.example.demo.modules.Eventos.model.Entity.Evento;
import com.example.demo.modules.Salones.Repository.SalonRepository;
import com.example.demo.modules.Salones.model.Entity.Salon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EventosService {

    private final EventosRepository eve_repo;
    private final ClientesRepository cli_repo;
    private final SalonRepository sal_repo;

    public EventosService(EventosRepository eve_repo, ClientesRepository cli_repo, SalonRepository sal_repo) {
        this.eve_repo = eve_repo;
        this.cli_repo = cli_repo;
        this.sal_repo = sal_repo;
    }

    private Evento convertiraEntity(EventosDTO dto){
        Evento ent = new Evento();
        Cliente cli_ent = cli_repo.findById(dto.getId_cliente()).orElseThrow(()-> new RuntimeException("Cliente no encontrado con id " + dto.getId_cliente()));
        ent.setId_cliente(cli_ent);
        Salon sal_ent = sal_repo.findById(dto.getId_salon()).orElseThrow(()-> new RuntimeException("Salón no encontrado con id " + dto.getId_salon()));
        ent.setId_salon(sal_ent);
        ent.setNombre_evento(dto.getNombre_evento());
        ent.setFecha_evento(dto.getFecha_evento());
        ent.setCantidad_personas(dto.getCantidad_personas());
        ent.setCantidad_horas(dto.getCantidad_horas());
        ent.setEstado(dto.getEstado());
        double total = sal_ent.getPrecio_renta() * dto.getCantidad_horas();
        ent.setTotal_pago(total);
        return ent;
    }

    private EventosDTO convertiraDTO(Evento ent){
        EventosDTO dto = new EventosDTO();
        dto.setId_evento(ent.getId_evento());
        if(ent.getId_cliente()!=null){
            dto.setId_cliente(ent.getId_cliente().getId_cliente());
        }
        if(ent.getId_salon()!=null){
            dto.setId_salon(ent.getId_salon().getId_salon());
        }
        dto.setNombre_evento(ent.getNombre_evento());
        dto.setFecha_evento(ent.getFecha_evento());
        dto.setCantidad_personas(ent.getCantidad_personas());
        dto.setCantidad_horas(ent.getCantidad_horas());
        dto.setEstado(ent.getEstado());
        dto.setTotal_pago(ent.getTotal_pago());
        return dto;
    }

    //POST
    public EventosDTO crearEvento(EventosDTO dto){
        Evento ent = convertiraEntity(dto);
        Evento nuevoEvento = eve_repo.save(ent);
        return convertiraDTO(nuevoEvento);
    }

    //GET
    public List<EventosDTO> listarEventos(){
        List<Evento> list = eve_repo.findAll();
        List<EventosDTO> listDTO = new ArrayList<>();
        for(Evento ent : list){
            listDTO.add(convertiraDTO(ent));
        }
        return listDTO;
    }

    //GETBYID
    public EventosDTO obtenerEvento(Long id){
        Evento ent = eve_repo.findById(id).orElseThrow(()-> new RuntimeException("Evento no encontrado con id " + id));
        return convertiraDTO(ent);
    }

    //PUT
    public EventosDTO modificarEvento(Long id, EventosDTO dto){
        Evento ent = eve_repo.findById(id).orElseThrow(()-> new RuntimeException("Evento no encontrado con id " + id));
        Cliente cli_ent = cli_repo.findById(dto.getId_cliente()).orElseThrow(()-> new RuntimeException("Cliente no encontrado con id " + dto.getId_cliente()));
        ent.setId_cliente(cli_ent);
        Salon sal_ent = sal_repo.findById(dto.getId_salon()).orElseThrow(()-> new RuntimeException("Salón no encontrado con id " + dto.getId_salon()));
        ent.setId_salon(sal_ent);
        ent.setNombre_evento(dto.getNombre_evento());
        ent.setFecha_evento(dto.getFecha_evento());
        ent.setCantidad_personas(dto.getCantidad_personas());
        ent.setCantidad_horas(dto.getCantidad_horas());
        ent.setEstado(dto.getEstado());
        double total = sal_ent.getPrecio_renta() * dto.getCantidad_horas();
        ent.setTotal_pago(total);
        Evento actualizado = eve_repo.save(ent);
        return convertiraDTO(actualizado);
    }

    //DELETE
    public void eliminarEvento(Long id){
        Evento ent = eve_repo.findById(id).orElseThrow();
        if(ent!=null){
            eve_repo.delete(ent);
        }
    }
}
