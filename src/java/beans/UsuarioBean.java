/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.Usuario;
import util.UsuarioDAO;

/**
 *
 * @author Irandir
 */
@ManagedBean
public class UsuarioBean {
    
    private Usuario usuario = new Usuario();
    
    public String enviar() {
        List<Usuario> usuarios = new UsuarioDAO().selectAll();
        boolean loginCorreto = false;
        for (Usuario usuario1 : usuarios) {
            if(usuario1.getLogin().equals(usuario.getLogin()) && usuario1.getSenha().equals(usuario.getSenha())){
                loginCorreto = true;
                break;
            }
        }
        if(loginCorreto == true){
            return "menu";
        }else{
            FacesMessage facesMensage = new FacesMessage("Login e/ou Senha incorretos.");
            FacesContext.getCurrentInstance().addMessage("erro", facesMensage);
            return null;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
