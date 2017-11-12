/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
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
public class LogadoBean {
     public void redireciona() throws IOException{
        Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(usuario==null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        
    }
}
