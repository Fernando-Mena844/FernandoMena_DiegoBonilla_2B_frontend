package com.example.demo.modules.Salones.Controller;

import com.example.demo.modules.Salones.Service.SalonService;
import com.example.demo.modules.Salones.model.DTO.SalonDTO;
import com.example.demo.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/salones")
@Slf4j
public class SalonController {

    private final SalonService service;

    public SalonController(SalonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SalonDTO>>> obtenerSalones(){
        try{
            List<SalonDTO> list = service.listarSalones();
            ApiResponse<List<SalonDTO>> response = new ApiResponse<>(true, "Salones listados correctamente.", list);
            log.info("Salones listados correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<SalonDTO>> responseError = new ApiResponse<>(false, "Error al listar los salones.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //GETBYID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SalonDTO>> obtenerSalon(@PathVariable Long id){
        try{
            SalonDTO dto = service.obtenerSalon(id);
            ApiResponse<SalonDTO> response = new ApiResponse<>(true, "Salón obtenido correctamente", dto);
            log.info("Salón obtenido correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<SalonDTO> responseError = new ApiResponse<>(false, "Error al obtener el salón");
            log.info("Error al obtener el salón");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }
}
