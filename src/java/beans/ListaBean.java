/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author Irandir
 */
@ManagedBean
@SessionScoped
public class ListaBean {
    public String redireciona(){
        Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(usuario.getNivelDeAcesso().equals("adm") || usuario.getNivelDeAcesso().equals("lid")){
            return "lista.xhtml";
        }
        return null;
    }
}
