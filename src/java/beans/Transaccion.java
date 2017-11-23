/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author iaw26540084
 */
public class Transaccion {
    
    private String id;
    private String fecha;
    private String cantidad;
    private String origen;
    private String destino;

    public Transaccion(String id, String fecha, String cantidad, String origen, String destino) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.origen = origen;
        this.destino = destino;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.fecha);
        hash = 79 * hash + Objects.hashCode(this.cantidad);
        hash = 79 * hash + Objects.hashCode(this.origen);
        hash = 79 * hash + Objects.hashCode(this.destino);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaccion other = (Transaccion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        if (!Objects.equals(this.origen, other.origen)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + ", origen=" + origen + ", destino=" + destino + '}';
    }
    
    
}
