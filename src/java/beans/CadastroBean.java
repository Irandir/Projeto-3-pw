/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.DAO.hibernates.EquipeHibernate;
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
@SessionScoped
public class CadastroBean {

    private Usuario usuario = new Usuario();
    private Pesquisador pesquisador = new Pesquisador();
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

    public String cadastrar() {

        if (pesquisadorLogado.getNivel().getNivel().equals("ADMINISTRADOR")) {
            Nivel nivel = new Nivel("LIDER");
            Equipe eq = new Equipe(pesquisador.getNome(), nivel);

            pesquisador.setEquipe(eq);
            pesquisador.setNivel(nivel);
            pesquisador.setUsuario(usuario);

            new UsuarioHibernate().insert(usuario);
            new EquipeHibernate().insert(eq);
            new PesquisadorHibernate().insert(pesquisador);
        } else {
            Nivel nivel = new Nivel("ESTAGIARIO");
            pesquisador.setEquipe(pesquisadorLogado.getEquipe());
            pesquisador.setNivel(nivel);
            pesquisador.setUsuario(usuario);

            new UsuarioHibernate().insert(usuario);
            new PesquisadorHibernate().insert(pesquisador);
        }
        return "concluido";
    }

    public String verificaNivel() {
        pesquisadorLogado = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");
        if (pesquisadorLogado.getNivel().getNivel().equals("ESTAGIARIO")) {
            return null;
        }
        return "cadastro.xhtml";
    }

    public String quem() {
        if (pesquisadorLogado.getNivel().getNivel().equals("ADMINISTRADOR")) {
            return "Líder";
        }
        return "Estagiário";
    }

}
