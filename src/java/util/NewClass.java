/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Date;
import model.DAO.hibernates.CicloHibernate;
import model.DAO.hibernates.MulherHibernate;
import model.DAO.hibernates.NivelHibernate;
import model.DAO.hibernates.UsuarioHibernate;
import model.pojo.Ciclo;
import model.pojo.Equipe;
import model.pojo.Mulher;
import model.pojo.Nivel;
import model.pojo.NivelAcessoEnum;
import model.pojo.Pesquisador;
import model.pojo.TipoEnum;
import model.pojo.Usuario;

/**
 *
 * @author herik
 */
public class NewClass {
    public static void main(String[] args) {
        /*CicloHibernate c = new CicloHibernate();
        c.insert(new Ciclo(TipoEnum.REGULAR.toString()));
        System.out.println(c.recuperarPorNome("REGULAR"));
        System.out.println(c.recuperarTodos());*/
    
        Pesquisador p = new Pesquisador();
        p.setId(0);
        p.setEquipe(new Equipe("", new Nivel("LIDER")));
        p.setNome("");
        Usuario usuario = new Usuario("","");
        usuario.setId(37);
        p.setUsuario(usuario);
        Ciclo c = new Ciclo();
        c.setTipo("Regular");
        Mulher m = new Mulher(2, "dsggfgfh", new Date(), c, new Date(), new Date(), null);
        MulherHibernate u = new MulherHibernate();
        
        u.insert(m);
        
    }
}
