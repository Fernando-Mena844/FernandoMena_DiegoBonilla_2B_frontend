package com.example.demo.modules.Eventos.Controller;

import com.example.demo.modules.Eventos.Service.EventosService;
import com.example.demo.modules.Eventos.model.DTO.EventosDTO;
import com.example.demo.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/eventos")
public class EventosController {
    private final EventosService service;

    public EventosController(EventosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EventosDTO>> nuevoEvento(@RequestBody @Valid EventosDTO json){
        try{
            EventosDTO dto = service.crearEvento(json);
            ApiResponse<EventosDTO> response = new ApiResponse<>(true, "Evento creado correctamente", dto);
            log.info("Evento creado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<EventosDTO> responseError = new ApiResponse<>(false, "Error al crear el evento", json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //GET
    @GetMapping
    public ResponseEntity<ApiResponse<List<EventosDTO>>> obtenerEventos(){
        try{
            List<EventosDTO> list = service.listarEventos();
            ApiResponse<List<EventosDTO>> response = new ApiResponse<>(true, "Eventos listados correctamente", list);
            log.info("Eventos listados correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<EventosDTO>> responseError = new ApiResponse<>(false, "No se pudo listar los eventos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //GETBYID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventosDTO>> obtenerEvento(@PathVariable Long id){
        try{
            EventosDTO dto = service.obtenerEvento(id);
            if(dto!=null && id!=null){
                ApiResponse<EventosDTO> response = new ApiResponse<>(true, "Evento obtenido correctamente", dto);
                log.info("Evento con ID " + id + " obtenido correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                log.info("No existe el evento con ID "+id);
                ApiResponse<EventosDTO> notFound = new ApiResponse<>(false,"No evento el evento con ID "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<EventosDTO> responseError = new ApiResponse<>(false, "No se pudo obtener el evento con ID " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventosDTO>> actualizarEvento(@PathVariable Long id, @RequestBody @Valid EventosDTO json){
        try{
            EventosDTO dto = service.obtenerEvento(id);
            if(dto!=null){
                EventosDTO updated = service.modificarEvento(id, json);
                ApiResponse<EventosDTO> response = new ApiResponse<>(true, "Evento editado correctamente", updated);
                log.info("El evento con ID " + id + " fue actualizado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                log.info("No existe el evento con ID "+id);
                ApiResponse<EventosDTO> notFound = new ApiResponse<>(false,"No existe el evento con ID "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<EventosDTO> responseError = new ApiResponse<>(false, "No se pudo actualizar el evento con ID " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<EventosDTO>> eliminarEvento(@PathVariable Long id){
        try{
            EventosDTO dto = service.obtenerEvento(id);
            if(dto!=null){
                service.eliminarEvento(id);
                ApiResponse<EventosDTO> response = new ApiResponse<>(true, "Evento eliminado correctamente");
                log.info("Evento con ID " + id + " eliminado correctamente");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }else{
                log.info("No existe el evento con ID "+id);
                ApiResponse<EventosDTO> notFound = new ApiResponse<>(false,"No existe el evento con ID "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<EventosDTO> responseError = new ApiResponse<>(false, "No se pudo eliminar el evento con ID " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }
}
