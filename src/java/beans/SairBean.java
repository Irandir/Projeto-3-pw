/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Irandir
 */
@ManagedBean
public class SairBean {
    public void logooff(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pesquisador", null);
    }
}
