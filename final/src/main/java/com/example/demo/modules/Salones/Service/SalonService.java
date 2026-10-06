package com.example.demo.modules.Salones.Service;

import com.example.demo.modules.Salones.Repository.SalonRepository;
import com.example.demo.modules.Salones.model.DTO.SalonDTO;
import com.example.demo.modules.Salones.model.Entity.Salon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SalonService {
    private final SalonRepository repo;

    private SalonService(SalonRepository repo) {
        this.repo = repo;
    }

    private Salon convertiraEntity(SalonDTO dto){
        Salon ent = new Salon();
        ent.setNombre_salon(dto.getNombre_salon());
        ent.setCapacidad(dto.getCapacidad());
        ent.setPrecio_renta(dto.getPrecio_renta());
        ent.setUbicacion(dto.getUbicacion());
        return ent;
    }
    private SalonDTO convertiraDTO(Salon ent){
        SalonDTO dto = new SalonDTO();
        dto.setId_salon(ent.getId_salon());
        dto.setNombre_salon(ent.getNombre_salon());
        dto.setCapacidad(ent.getCapacidad());
        dto.setPrecio_renta(ent.getPrecio_renta());
        dto.setUbicacion(ent.getUbicacion());
        return dto;
    }

    //GET
    public List<SalonDTO> listarSalones(){ //Pendiente de arreglar
        List<Salon> list = repo.findAll();
        List<SalonDTO> listDTO = new ArrayList<>();
        for(Salon ent : list){
            listDTO.add(convertiraDTO(ent));
        }
        return listDTO;
    }

    //GETBYID
    public SalonDTO obtenerSalon(Long id){ //Pendiente de arreglar
        Salon ent = repo.findById(id).orElseThrow(()-> new RuntimeException("Salón no encontrado con id " + id));
        return convertiraDTO(ent);
    }
}
