package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dao;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.ClientEntity;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientDao extends JpaRepository<ClientEntity, Long> {

    @Query("from RegionEntity")
    public List<RegionEntity> findAllRegiones();

    @Modifying
    @Query(value= "delete from  ClientEntity  where id= :id")
    public void deleteClientById(@Param("id") Long id);
}
