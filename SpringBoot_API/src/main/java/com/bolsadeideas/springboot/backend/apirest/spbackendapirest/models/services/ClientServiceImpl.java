package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.services;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.controller.ClientRestController;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dao.IClientDao;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dao.IProductoDao;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.ClientEntity;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.FacturaEntity;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.ProductoEntity;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.RegionEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl {

    private final IClientDao clienteDao;
    private final IFacturaDao facturaDao;
    private final IProductoDao productoDao;
    private final Logger log= LoggerFactory.getLogger(ClientServiceImpl.class);

    public final static String DIRECTORIO_UPLOAD = "uploads";

    @Transactional(readOnly = true)
    public List<ClientEntity> findAll() {

        return (List<ClientEntity>) clienteDao.findAll();
    }

    @Transactional(readOnly = true)
    public Page<ClientEntity> findAll(Pageable pageable) {

        return clienteDao.findAll(pageable);
    }

    public ClientEntity save(ClientEntity client){
        return clienteDao.save(client);
    }

    public ClientEntity findById(Long id){
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional()
    public void delete(Long id){
        log.info(id.toString());
        clienteDao.deleteById(id);
    }

    public Resource cargar (String nombreFoto) throws MalformedURLException{
        Path rutaArchivo= getPath(nombreFoto);
        log.info(rutaArchivo.toString());

        Resource recurso = new UrlResource(rutaArchivo.toUri());

        if (!recurso.exists() && !recurso.isReadable()){
            rutaArchivo= Paths.get("src/main/resources/static/images").resolve("no_user.png").toAbsolutePath();

            recurso = new UrlResource(rutaArchivo.toUri());
            log.error("Error no se pudo cargar la imagen: "+nombreFoto);


        }
        return recurso;
    }
    public String copiar (MultipartFile archivo) throws IOException {
        String nombreArchivo= UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", "");
        Path rutaArchivo= getPath(nombreArchivo);
        log.info(rutaArchivo.toString());

        Files.copy(archivo.getInputStream(), rutaArchivo);

        return nombreArchivo;
    }

    public boolean eliminar (String nombreFoto){
        if(nombreFoto!=null && !nombreFoto.isEmpty()){
            Path rutaFotoAnterior= getPath(nombreFoto);
            File archivoFotoAnterior= rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Transactional(readOnly = true)
    public FacturaEntity findFacturaById(Long id){
        return facturaDao.findById(id).orElse(null);

    }
    
    public FacturaEntity saveFactura(FacturaEntity factura){
        return facturaDao.save(factura);
    }
    public void deleteFacturaById (Long id){
        facturaDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductoEntity> findProductoByNombre(String nombre){
        return productoDao.findByNombreStartingWithIgnoreCase(nombre);
    }
    public Path getPath (String nombrefoto){
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombrefoto).toAbsolutePath();
    }

    @Transactional(readOnly = true)
    public List<RegionEntity> findAllRegiones(){
        return clienteDao.findAllRegiones();
    }
}
