package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dao;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.FacturaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IFacturaDao extends CrudRepository<FacturaEntity, Long> {

    @Modifying
    @Query(value= "delete from  FacturaEntity  where id= :id")
    public void deleteFacturaById(@Param("id") Long id);
}
