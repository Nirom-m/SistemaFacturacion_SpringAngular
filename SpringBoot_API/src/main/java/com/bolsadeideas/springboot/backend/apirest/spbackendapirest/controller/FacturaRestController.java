package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.controller;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dao.IClientDao;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.FacturaEntity;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.ProductoEntity;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.services.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FacturaRestController {
    private final ClientServiceImpl clientService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FacturaEntity show (@PathVariable Long id){
        return clientService.findFacturaById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        clientService.deleteFacturaById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/facturas/filtrar-productos/{term}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoEntity> filtrarProductos(@PathVariable String term){
        return clientService.findProductoByNombre(term);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/facturas")
    @ResponseStatus(HttpStatus.CREATED)
    public FacturaEntity create(@RequestBody FacturaEntity factura){
        return clientService.saveFactura(factura);
    }
}
