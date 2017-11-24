/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.pojo.Pesquisador;

/**
 *
 * @author Irandir
 */
@ManagedBean
public class EquipeBean {
    
     public String verificaNivel() {
        Pesquisador pesquisadorLogado  = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");
        if (!pesquisadorLogado.getNivel().getNivel().equals("ESTAGIARIO")) {
            return "equipe.xhtml";
        }
        return null;
    }
}
