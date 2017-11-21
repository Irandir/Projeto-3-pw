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
import javax.persistence.OneToOne;

/**
 *
 * @author Hérikles
 */
@Entity
public class Pesquisador implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    
    @OneToOne
    private Nivel nivel;
    @OneToOne
    private Equipe equipe;
    @OneToOne
    private Usuario usuario;

    public Pesquisador() {
    }

    public Pesquisador(String nome, Nivel nivel, Equipe equipe,Usuario usuario) {
        this.nome = nome;
        this.nivel = nivel;
        this.equipe = equipe;
        this.usuario = usuario;
    }
    
    public int getId(){
        return this.id;
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

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return "Pesquisador{" + "id=" + id + ", nome=" + nome + ", nivel=" + nivel + ", equipe=" + equipe + ", usuario=" + usuario + '}';
    }

    
    
}
