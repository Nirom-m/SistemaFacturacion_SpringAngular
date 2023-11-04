package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dao;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.ProductoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoDao extends CrudRepository<ProductoEntity, Long> {

    @Query("select p from ProductoEntity  p where p.nombre like %?1%")
    public List<ProductoEntity> findByNombre(String nombre);
    public List<ProductoEntity> findByNombreStartingWithIgnoreCase(String nombre);

}
