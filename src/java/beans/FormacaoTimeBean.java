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
import model.DAO.hibernates.PesquisadorHibernate;
import model.pojo.Pesquisador;

/**
 *
 * @author Irandir
 */
@ManagedBean
@SessionScoped
public class FormacaoTimeBean {
    private List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
    private List<String> pesquisadoresEscolhidos = new ArrayList<>();
    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }
    
}
