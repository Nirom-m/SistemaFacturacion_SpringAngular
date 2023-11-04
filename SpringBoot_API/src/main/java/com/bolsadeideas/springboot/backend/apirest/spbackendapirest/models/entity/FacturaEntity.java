package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="factura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descripcion;

    private String observacion;

    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @JsonIgnoreProperties(value = {"facturas", "hibernateLazyInitializer", "hendler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientEntity cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "hendler"})
    @JoinColumn(name="factura_id")
    private List<ItemFacturaEntity> items= new ArrayList<>();

    @Serial
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist(){
        createAt= new Date();
    }

    public Double getTotal(){
        Double total=0.0;
        for (ItemFacturaEntity item: items) {
            total+=item.getImporte();
        }
        return total;
    }

}
