/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.DAO.hibernates.EquipeHibernate;
import model.DAO.hibernates.PesquisadorHibernate;
import model.pojo.Equipe;
import model.pojo.Nivel;
import model.pojo.Pesquisador;

/**
 *
 * @author Irandir
 */
@ManagedBean
public class MinhaEquipeBean {

    private Pesquisador pesquisadorLogado = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");
    private String[] nomes;
    private List<String> estagiarosDiponiveis = estagiarosDiponiveis2();
    
//private Equipe equipe = new Equipe();
 
    public List<String> todosOsNomesEquipe() {
        
        List<String> resposta = new ArrayList<String>();
        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        for (Pesquisador pesquisador : pesquisadores) {
            if (pesquisador.getEquipe().getId() == pesquisadorLogado.getEquipe().getId()) {
                if (pesquisador.getId() == pesquisadorLogado.getId()) {
                    continue;
                }
                resposta.add(pesquisador.getNome());
            }
        }
        return resposta;
    }

    public List<Pesquisador> todosDaEquipe(){
        List<Pesquisador> resposta = new ArrayList<>();
        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        for (Pesquisador pesquisador : pesquisadores) {
            if (pesquisador.getEquipe().getId() == pesquisadorLogado.getEquipe().getId()) {
                if (pesquisador.getId() == pesquisadorLogado.getId()) {
                    continue;
                }
                resposta.add(pesquisador);
            }
        }
        return resposta;
    }
    
    
    public List<String> estagiarosDiponiveis2() {
        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        List<String> resposta = new ArrayList<String>();
        for (Pesquisador pesquisador : pesquisadores) {
            if (pesquisador.getEquipe().getNome().equals("Sem Equipe") && pesquisador.getNivel().getNivel().equals("ESTAGIARIO")) {
                resposta.add(pesquisador.getNome());
            }
        }
        return resposta;
    }

    public String editar() {
        if (pesquisadorLogado.getNivel().getNivel().equals("ADMINISTRADOR")) {
            return "false";
        }
        return "true";
    }

    public String atualizar() {
        if(nomes.length==0){
            return "";
        }
        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        PesquisadorHibernate pesquisadorHibernate = new PesquisadorHibernate();
        Pesquisador[] pesquisadoresSelecionados = new Pesquisador[nomes.length];
        for (int i = 0; i < pesquisadoresSelecionados.length; i++) {
            for (int j = 0; j < pesquisadores.size(); j++) {
                if (nomes[i].equals(pesquisadores.get(j).getNome())) {
                    pesquisadoresSelecionados[i] = pesquisadores.get(j);
                    pesquisadoresSelecionados[i].setEquipe(pesquisadorLogado.getEquipe());
                    pesquisadorHibernate.update(pesquisadoresSelecionados[i]);
                }
            }
        }

        return "concluido";
    }

    public String verificaEquipeMinha() {
        if (pesquisadorLogado.getEquipe().getNome().equals("Sem Equipe")) {
            return "";
        }
        return "minha_equipe";
    }

    public String mostrarMensagem() {

        if (pesquisadorLogado.getEquipe().getNome().equals("Sem Equipe")) {
            return "PF('dlg4').show();";
        }
        return "";
    }

    public Pesquisador getPesquisadorLogado() {
        return pesquisadorLogado;
    }

    public void setPesquisadorLogado(Pesquisador pesquisadorLogado) {
        this.pesquisadorLogado = pesquisadorLogado;
    }

    public String[] getNomes() {
        return nomes;
    }

    public void setNomes(String[] nomes) {
        this.nomes = nomes;
    }

    public List<String> getEstagiarosDiponiveis() {
        return estagiarosDiponiveis;
    }

    public void setEstagiarosDiponiveis(List<String> estagiarosDiponiveis) {
        this.estagiarosDiponiveis = estagiarosDiponiveis;
    }

}
