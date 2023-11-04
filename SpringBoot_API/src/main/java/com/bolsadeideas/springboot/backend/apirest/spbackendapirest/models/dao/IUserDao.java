package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dao;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<UsuarioEntity, Long> {

    public UsuarioEntity findByUsername(String username);
}
