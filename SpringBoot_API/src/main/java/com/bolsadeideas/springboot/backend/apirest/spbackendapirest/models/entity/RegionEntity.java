package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="region")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Serial
    private static final long serialVersionUID = 1L;


}
