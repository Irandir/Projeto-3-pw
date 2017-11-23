/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

/**
 *
 * @author Herikles
 */
@Entity
public class Equipe implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    @OneToOne
    private Nivel nivel;

    public Equipe() {
    }

    public Equipe(String nome,Nivel nivel) {
        this.nome = nome;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }


    
}
