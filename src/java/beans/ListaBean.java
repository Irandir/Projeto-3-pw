/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.DAO.hibernates.MulherHibernate;
import model.DAO.hibernates.NivelHibernate;
import model.DAO.hibernates.PesquisadorHibernate;
import model.pojo.Mulher;
import model.pojo.Nivel;
import model.pojo.NivelAcessoEnum;
import model.pojo.Pesquisador;
import model.pojo.Usuario;

/**
 *
 * @author Irandir
 */
@ManagedBean
@SessionScoped
public class ListaBean {

    private Pesquisador pesquisador;

    public String redireciona() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        pesquisador = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");

        if (!pesquisador.getNivel().getNivel().equals("ESTAGIARIO")) {

            return "lista.xhtml";
        }

        return null;
    }

    public List<Mulher> mulheres() {

        int id_da_equipe = pesquisador.getEquipe().getId();

        if (pesquisador.getNivel().getNivel().equals("ADMINISTRADOR")) {
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

}
