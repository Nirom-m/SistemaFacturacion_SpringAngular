package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.controller;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.ClientEntity;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.RegionEntity;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.services.ClientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientServiceImpl clientService;
    private final Logger log= LoggerFactory.getLogger(ClientRestController.class);
    @GetMapping("/clientes")
    public List<ClientEntity> index(){
        return clientService.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<ClientEntity> index(@PathVariable int page){

        return clientService.findAll(PageRequest.of(page, 4));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        ClientEntity client= null;
        Map<String, Object> response= new HashMap<>();
        try {
            client= clientService.findById(id);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al buscar el cliente en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (client==null){
            response.put("mensaje", "El cliente con ID "+id+" no se ha  encontrado en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ClientEntity>(client, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody ClientEntity client, BindingResult result){
        ClientEntity newClient= null;
        Map<String, Object> response= new HashMap<>();
        if(result.hasErrors()){
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(err->"El campo '"+err.getField()+"' "+ err.getDefaultMessage())
                    .toList();
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            newClient= clientService.save(client);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el cliente en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con exito");
        response.put("cliente", newClient);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("clientes/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@Valid @RequestBody ClientEntity clientUpdate, BindingResult result, @PathVariable Long id){
        Map<String, Object> response= new HashMap<>();
        ClientEntity client= clientService.findById(id);
        ClientEntity clienteEdited= null;
        if(client==null){
            response.put("mensaje", "El cliente con ID "+id+" no se ha  encontrado en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        if(result.hasErrors()){
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(err->"El campo '"+err.getField()+"' "+ err.getDefaultMessage())
                    .toList();
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            client.setNombre(clientUpdate.getNombre());
            client.setApellido(clientUpdate.getApellido());
            client.setEmail(clientUpdate.getEmail());
            client.setRegion(clientUpdate.getRegion());

            clienteEdited= clientService.save(client);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el cliente en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido modificado con exito!");
        response.put("cliente", clienteEdited);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String, Object> response= new HashMap<>();
        ClientEntity client= clientService.findById(id);
        if(client==null){
            response.put("mensaje", "El cliente con ID "+id+" no se ha  encontrado en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        try {
            String  nombreFotoAnterior= client.getFoto();

            clientService.eliminar(nombreFotoAnterior);
            clientService.delete(id);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar al cliente de la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente fue eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @PostMapping("clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        ClientEntity cliente= clientService.findById(id);
        Map<String, Object> response= new HashMap<>();
        String nombreArchivo="";
        if(!archivo.isEmpty()){

            try {
                nombreArchivo= clientService.copiar(archivo);

            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen como foto de perfil: "+ nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String  nombreFotoAnterior= cliente.getFoto();
            clientService.eliminar(nombreFotoAnterior);

            cliente.setFoto(nombreArchivo);
            clientService.save(cliente);
            response.put("cliente", cliente);
            response.put("mensaje", "Haz subido correctamente la imagen: "+ nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto (@PathVariable String nombreFoto){
        Resource recurso;
        try {
            recurso= clientService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders cabecera= new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
    @GetMapping("/clientes/regiones")
    public List<RegionEntity> listarRegiones(){
        return clientService.findAllRegiones();
    }
}
