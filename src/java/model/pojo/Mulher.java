/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Herikles
 */
@Entity
public class Mulher implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nascimento;
    @OneToOne
    private Ciclo tipo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ultimaMenstruacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    //não lembro o que é esse PM
    private Date pM;

    @ManyToOne
    private Pesquisador pesquisador;

    public Mulher() {
    }

    public Mulher(Integer id, String nome, Date nascimento, Ciclo tipo, Date ultimaMenstruacao, Date pM, Pesquisador pesquisador) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.tipo = tipo;
        this.ultimaMenstruacao = ultimaMenstruacao;
        this.pM = pM;
        this.pesquisador = pesquisador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Ciclo getTipo() {
        return tipo;
    }

    public void setTipo(Ciclo tipo) {
        this.tipo = tipo;
    }

    public Date getUltimaMenstruacao() {
        return ultimaMenstruacao;
    }

    public void setUltimaMenstruacao(Date ultimaMenstruacao) {
        this.ultimaMenstruacao = ultimaMenstruacao;
    }

    public Date getPM() {
        return pM;
    }

    public void setPM(Date pM) {
        this.pM = pM;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

   

   

    @Override
    public String toString() {
        return "Mulher{" + "id=" + id + ", nome=" + nome + ", nascimento=" + nascimento + ", tipo=" + tipo + ", ultimaMenstruacao=" + ultimaMenstruacao + ", pM=" + pM + ", pesquisador=" + pesquisador + '}';
    }

}
