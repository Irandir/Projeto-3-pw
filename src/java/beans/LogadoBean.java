/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.DAO.hibernates.PesquisadorHibernate;
import model.pojo.Pesquisador;
import model.pojo.Usuario;


/**
 *
 * @author Irandir
 */
@ManagedBean
@SessionScoped
public class LogadoBean {
    
    private Usuario usuario; 
    
  
    
    public void redireciona() throws IOException{
        usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        if(usuario==null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }else{
            List<Pesquisador> pesquisadores = new PesquisadorHibernate().recuperarTodos();
            for (Pesquisador pesquisador : pesquisadores) {
                if(pesquisador.getUsuario().getId() == usuario.getId()){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pesquisador", pesquisador);
                }
            }
        }
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String nomeDoUsuario() {
       return ((Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador")).getNome();
    }

   
    
}
