/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.DAO.hibernates.EquipeHibernate;
import model.DAO.hibernates.NivelHibernate;
import model.DAO.hibernates.PesquisadorHibernate;
import model.DAO.hibernates.UsuarioHibernate;
import model.pojo.Equipe;
import model.pojo.Nivel;
import model.pojo.Pesquisador;
import model.pojo.Usuario;

/**
 *
 * @author Irandir
 */
@ManagedBean
public class CadastroBean {

    private Usuario usuario = new Usuario();
    private Pesquisador pesquisador = new Pesquisador();
    private Nivel nivel = new Nivel(); 
    private Pesquisador pesquisadorLogado;

    public CadastroBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    public String cadastrar() {

        EquipeHibernate equipeHibernate = new EquipeHibernate();
        List<Equipe> semEquipe = equipeHibernate.recuperarPorNome("Sem Equipe");
        Equipe eq;
        if (semEquipe.size()==0) {
            
            Nivel nivel0 = new Nivel("");
            eq = new Equipe("Sem Equipe", nivel0);
            new NivelHibernate().insert(nivel0);
            equipeHibernate.insert(eq);
        } else {
           
            eq = semEquipe.get(0);
        }
        pesquisador.setEquipe(eq);
        pesquisador.setNivel(nivel);
        pesquisador.setUsuario(usuario);

        new UsuarioHibernate().insert(usuario);

        new PesquisadorHibernate().insert(pesquisador);

        return "concluido";
    }

    public String verificaNivel() {
        pesquisadorLogado = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");
        if (pesquisadorLogado.getNivel().getNivel().equals("ADMINISTRADOR")) {
            return "cadastro.xhtml";
        }
        return null;
    }

}
