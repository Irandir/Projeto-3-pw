
import model.Usuario;
import util.UsuarioDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Irandir
 */
public class TesteUsuario {
    public static void main(String[] args) {
        Usuario crack = new Usuario();
        crack.setLogin("a");
        crack.setSenha("1");
        new UsuarioDAO().insert(crack);
    }
}
