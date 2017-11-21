/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.DAO.hibernates.MulherHibernate;
import model.DAO.hibernates.PesquisadorHibernate;
import model.pojo.Mulher;
import model.pojo.Pesquisador;
import model.pojo.Usuario;

/**
 *
 * @author Irandir
 */
@ManagedBean
@SessionScoped
public class ListaBean {


    private Pesquisador pesquisadorLogado;

    public String redireciona() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        pesquisadorLogado = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");

        if (!pesquisadorLogado.getNivel().getNivel().equals("ESTAGIARIO")) {

            return "lista.xhtml";
        }

        return null;
    }

    public List<Mulher> mulheres() {

        int id_da_equipe = pesquisadorLogado.getEquipe().getId();

        if (pesquisadorLogado.getNivel().getNivel().equals("ADMINISTRADOR")) {
            return new MulherHibernate().recuperarTodos();
        }

        List<Pesquisador> todos = new PesquisadorHibernate().recuperarTodos();
        List<Pesquisador> equipe = new ArrayList<>();
        for (Pesquisador membro : todos) {
            if (membro.getEquipe().getId() == id_da_equipe) {
                equipe.add(membro);

            }
        }

        List<Mulher> todas = new MulherHibernate().recuperarTodos();
        List<Mulher> entrevistadas = new ArrayList<>();
        for (Mulher mulher : todas) {
            for (Pesquisador membro : equipe) {
                if (membro.getId() == mulher.getPesquisador().getId()) {
                    entrevistadas.add(mulher);

                }
            }
        }
        return entrevistadas;
    }

    public Pesquisador getPesquisador() {
        return pesquisadorLogado;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisadorLogado = pesquisador;
    }

    public Pesquisador getPesquisadorLogado() {
        return pesquisadorLogado;
    }

    public void setPesquisadorLogado(Pesquisador pesquisadorLogado) {
        this.pesquisadorLogado = pesquisadorLogado;
    }

    public String formateData(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

}
