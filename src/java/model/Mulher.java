package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Mulher {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nascimento;
    private String tipo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date uM;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date pM;

    public Mulher(Integer id, String nome, Date nascimento, String tipo, Date uM, Date pM) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.tipo = tipo;
        this.uM = uM;
        this.pM = pM;
    }

    public Mulher() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getUM() {
        return uM;
    }

    public void setUM(Date uM) {
        this.uM = uM;
    }

   

    public Date getPM() {
        return pM;
    }

    public void setPM(Date pM) {
        this.pM = pM;
    }

    @Override
    public String toString() {
        return "Mulher{" + "id=" + id + ", nome=" + nome + ", nascimento=" + nascimento + ", tipo=" + tipo + ", uM=" + uM + ", pM=" + pM + '}';
    }

}
