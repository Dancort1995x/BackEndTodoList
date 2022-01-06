package com.example.project.todolistapp.model;

import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Builder
@Table(name="listas")
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long identificador;

    @NotNull
    @NotEmpty
    @Column
    private String descripcion;

    @CreationTimestamp
    @Column
    private Timestamp fechaCreacion;

    @NotNull
    @Column
    private boolean vigente;


    public Lista() {
    }

    public Lista(long identificador, String descripcion, Timestamp fechaCreacion, boolean vigente) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.vigente = vigente;
    }

    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }


}
