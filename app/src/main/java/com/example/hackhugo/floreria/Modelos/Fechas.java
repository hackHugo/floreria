package com.example.hackhugo.floreria.Modelos;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hackhugo on 05/12/2016.
 */

public class Fechas extends RealmObject {
    @PrimaryKey
    Integer id;
    String Fecha;
    String Descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
