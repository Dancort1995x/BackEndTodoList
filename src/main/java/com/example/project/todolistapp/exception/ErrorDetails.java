package com.example.project.todolistapp.exception;

import java.util.Date;

public class ErrorDetails {

    private Date fecha;
    private String msg;
    private String detalles;

    public ErrorDetails(Date fecha, String msg, String detalles) {
        this.fecha = fecha;
        this.msg = msg;
        this.detalles = detalles;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
