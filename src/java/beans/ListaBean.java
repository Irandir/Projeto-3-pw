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

    public List<Mulher> mulheres() {
        Pesquisador pesquisadorLogado = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");

        if (pesquisadorLogado.getNivel().getNivel().equals("ADMINISTRADOR")) {
            return new MulherHibernate().recuperarTodos();
        }

        List<Mulher> todas = new MulherHibernate().recuperarTodos();
        List<Mulher> entrevistadas = new ArrayList<>();
        if (pesquisadorLogado.getNivel().getNivel().equals("ESTAGIARIO")) {
            for (Mulher mulher : todas) {
                if (pesquisadorLogado.getId() == mulher.getPesquisador().getId()) {
                    entrevistadas.add(mulher);
                }
            }
            return entrevistadas;
        }

        int id_da_equipe = pesquisadorLogado.getEquipe().getId();
        List<Pesquisador> todos = new PesquisadorHibernate().recuperarTodos();
        List<Pesquisador> equipe = new ArrayList<>();
        for (Pesquisador membro : todos) {
            if (membro.getEquipe().getId() == id_da_equipe) {
                equipe.add(membro);

            }
        }

        for (Mulher mulher : todas) {
            for (Pesquisador membro : equipe) {
                if (membro.getId() == mulher.getPesquisador().getId()) {
                    entrevistadas.add(mulher);

                }
            }
        }
        return entrevistadas;
    }

    public String formateData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

}
