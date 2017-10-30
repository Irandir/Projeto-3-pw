/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import model.Usuario;

import org.hibernate.Session;

/**
 *
 * @author Irandir
 */
public class UsuarioDAO {
    private Session session;
    FabricaDeConexao fabrica;
    public void insert(Usuario usuario) {
        fabrica = new FabricaDeConexao();
        session = fabrica.novaConexao();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
        fabrica.getFactory().close();
    }

    public List<Usuario> selectAll() {
        fabrica = new FabricaDeConexao();
        session = fabrica.novaConexao();
        session.getTransaction().commit();
        
        List<Usuario> usuarios = session.createQuery("from Usuario").list();

        session.close();
        fabrica.getFactory().close();
        return usuarios;
    }
}
