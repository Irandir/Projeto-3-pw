/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Hérikles
 */
@Entity
public class Ciclo implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String tipo;

    public Ciclo() {
    }

    public Ciclo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Ciclo{" + "id=" + id + ", tipo=" + tipo + '}';
    }
    
    
}
