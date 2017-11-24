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
public class NovaEquipeBean2 {

    private List<String> pesquisadoresNomeEst = addEstagiaros();
    private String[] pesquisadoresNomeEstSelecionados;
    private List<String> pesquisadoresNomeNivelAlto= addMestres();
    private String nomeDoPesquisador;

    private Pesquisador pesquisadorLogado = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");
    private Equipe equipe = new Equipe();
  

    public List<String> addEstagiaros(){
        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        List<String> resposta= new ArrayList<String>();
        for (Pesquisador pesquisador : pesquisadores) {
            if(pesquisador.getEquipe().getNome().equals("Sem Equipe") && pesquisador.getNivel().getNivel().equals("ESTAGIARIO")){
                resposta.add(pesquisador.getNome());
            }
        }
        return resposta;
    }

    public List<String> addMestres(){
        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        List<String> resposta= new ArrayList<String>();
        for (Pesquisador pesquisador : pesquisadores) {
            if(pesquisador.getEquipe().getNome().equals("Sem Equipe") && !pesquisador.getNivel().getNivel().equals("ESTAGIARIO")){
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

    public String atualizar(){
        List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
        EquipeHibernate equipeHibernate = new EquipeHibernate();
        PesquisadorHibernate pesquisadorHibernate = new PesquisadorHibernate();
        
        Pesquisador pesquisadorLider = new Pesquisador();
        if (pesquisadorLogado.getNivel().getNivel().equals("LIDER") && pesquisadorLogado.getEquipe().getNome().equals("Sem Equipe")) {
            pesquisadorLider = pesquisadorLogado;
        } else if (pesquisadoresNomeNivelAlto.size()>0) {
            
            for (Pesquisador pesquisadorAux : pesquisadores) {
                if (pesquisadorAux.getNome().equals(nomeDoPesquisador)) {
                    pesquisadorLider = pesquisadorAux;
                }
            }
        } 

        equipe.setNivel(pesquisadorLider.getNivel());
        pesquisadorLider.setEquipe(equipe);

        Pesquisador[] pesquisadoresSelecionados = new Pesquisador[pesquisadoresNomeEstSelecionados.length];
        for (int i = 0; i < pesquisadoresSelecionados.length; i++) {
            for (int j = 0; j < pesquisadores.size(); j++) {
                if (pesquisadoresNomeEstSelecionados[i].equals(pesquisadores.get(j).getNome())) {
                    pesquisadoresSelecionados[i] = pesquisadores.get(j);
                    pesquisadoresSelecionados[i].setEquipe(equipe);
                }
            }
        }

        equipeHibernate.insert(equipe);

        pesquisadorHibernate.update(pesquisadorLider);
        for (Pesquisador pesquisadoresSelecionado : pesquisadoresSelecionados) {
            pesquisadorHibernate.update(pesquisadoresSelecionado);
        }
        return "concluido";
    }

    public String verificaSePodeCriarNovaEquipe(){
        if(pesquisadoresNomeEst.size()==0){
            return "";
        }else if(pesquisadoresNomeNivelAlto.size()==0){
          return "";
      }else if(pesquisadorLogado.getNivel().getNivel().equals("LIDER") && !pesquisadorLogado.getEquipe().getNome().equals("Sem Equipe")){
          return "";
      }
        return "nova_equipe2";
    }

    public String mostrarMensagem(){
       
      if(pesquisadoresNomeEst.size()==0){
            return "PF('dlg1').show();";
      }else if(pesquisadoresNomeNivelAlto.size()==0){
          return "PF('dlg2').show();";
      }else if(pesquisadorLogado.getNivel().getNivel().equals("LIDER") && !pesquisadorLogado.getEquipe().getNome().equals("Sem Equipe")){
          return "PF('dlg3').show();";
      }
        return "";
    }
    
    
    
    public List<String> getPesquisadoresNomeEst() {
        return pesquisadoresNomeEst;
    }

    public void setPesquisadoresNomeEst(List<String> pesquisadoresNomeEst) {
        this.pesquisadoresNomeEst = pesquisadoresNomeEst;
    }

    public String[] getPesquisadoresNomeEstSelecionados() {
        return pesquisadoresNomeEstSelecionados;
    }

    public void setPesquisadoresNomeEstSelecionados(String[] pesquisadoresNomeEstSelecionados) {
        this.pesquisadoresNomeEstSelecionados = pesquisadoresNomeEstSelecionados;
    }

 
    public Pesquisador getPesquisadorLogado() {
        return pesquisadorLogado;
    }

    public void setPesquisadorLogado(Pesquisador pesquisadorLogado) {
        this.pesquisadorLogado = pesquisadorLogado;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public List<String> getPesquisadoresNomeNivelAlto() {
        return pesquisadoresNomeNivelAlto;
    }

    public void setPesquisadoresNomeNivelAlto(List<String> pesquisadoresNomeNivelAlto) {
        this.pesquisadoresNomeNivelAlto = pesquisadoresNomeNivelAlto;
    }

    public String getNomeDoPesquisador() {
        return nomeDoPesquisador;
    }

    public void setNomeDoPesquisador(String nomeDoPesquisador) {
        this.nomeDoPesquisador = nomeDoPesquisador;
    }
    
}
