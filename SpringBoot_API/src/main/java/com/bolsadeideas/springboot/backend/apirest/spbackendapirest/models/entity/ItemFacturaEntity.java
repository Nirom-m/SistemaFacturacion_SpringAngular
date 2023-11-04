package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="factura_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemFacturaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "hendler "})
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @Serial
    private static final long serialVersionUID = 1L;

    public Double getImporte(){
        return Double.valueOf(cantidad)*producto.getPrecio();
    }
}
