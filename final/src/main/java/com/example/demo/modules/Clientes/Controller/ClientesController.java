package com.example.demo.modules.Clientes.Controller;

import com.example.demo.modules.Clientes.Service.ClientesService;
import com.example.demo.modules.Clientes.model.DTO.ClientesDTO;
import com.example.demo.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Slf4j
public class ClientesController {

    private final ClientesService service;

    public ClientesController(ClientesService service) {
        this.service = service;
    }

    //POST
    @PostMapping
    public ResponseEntity<ApiResponse<ClientesDTO>> nuevoCliente(@RequestBody @Valid ClientesDTO json){
        try{
            ClientesDTO dto = service.crearCliente(json);
            ApiResponse<ClientesDTO> response = new ApiResponse<>(true, "Cliente creado correctamente", dto);
            log.info("Cliente creado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ClientesDTO> responseError = new ApiResponse<>(false, "Error al crear el cliente", json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //GET
    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientesDTO>>> obtenerClientes(){
        try{
            List<ClientesDTO> list = service.listarClientes();
            ApiResponse<List<ClientesDTO>> response = new ApiResponse<>(true, "Clientes listados correctamente", list);
            log.info("Clientes listados correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<ClientesDTO>> responseError = new ApiResponse<>(false, "No se pudo listar los clientes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //GETBYID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>> obtenerCliente(@PathVariable Long id){
        try{
            ClientesDTO dto = service.obtenerCliente(id);
            if(dto!=null){
                ApiResponse<ClientesDTO> response = new ApiResponse<>(true, "Cliente obtenido correctamente", dto);
                log.info("Cliente con ID " + id + " obtenido correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                log.info("No existe el cliente con ID "+id);
                ApiResponse<ClientesDTO> notFound = new ApiResponse<>(false,"No existe el cliente con ID "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ClientesDTO> responseError = new ApiResponse<>(false, "No se pudo obtener el cliente con ID " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>> actualizarCliente(@PathVariable Long id, @RequestBody @Valid ClientesDTO json){
        try{
            ClientesDTO dto = service.obtenerCliente(id);
            if(dto!=null){
                ClientesDTO updated = service.modificarCliente(id, json);
                ApiResponse<ClientesDTO> response = new ApiResponse<>(true, "Cliente editado correctamente", updated);
                log.info("El cliente con ID " + id + " fue actualizado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                log.info("No existe el cliente con ID "+id);
                ApiResponse<ClientesDTO> notFound = new ApiResponse<>(false,"No existe el cliente con ID "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ClientesDTO> responseError = new ApiResponse<>(false, "No se pudo actualizar el cliente con ID " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>> eliminarCliente(@PathVariable Long id){
        try{
            ClientesDTO dto = service.obtenerCliente(id);
            if(dto!=null){
                service.eliminarCliente(id);
                ApiResponse<ClientesDTO> response = new ApiResponse<>(true, "Cliente eliminado correctamente");
                log.info("Cliente con ID " + id + " eliminado correctamente");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }else{
                log.info("No existe el cliente con ID "+id);
                ApiResponse<ClientesDTO> notFound = new ApiResponse<>(false,"No existe el cliente con ID "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ClientesDTO> responseError = new ApiResponse<>(false, "No se pudo eliminar el cliente con ID " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }
}
