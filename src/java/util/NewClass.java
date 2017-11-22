/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Date;
import model.DAO.hibernates.CicloHibernate;
import model.DAO.hibernates.EquipeHibernate;
import model.DAO.hibernates.MulherHibernate;
import model.DAO.hibernates.NivelHibernate;
import model.DAO.hibernates.PesquisadorHibernate;
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
        Nivel nivel1 = new Nivel("ADMINISTRADOR");
        Nivel nivel2 = new Nivel("LIDER");
        Nivel nivel3 = new Nivel("ESTAGIARIO");
        
        Ciclo ciclo = new Ciclo("Regular");
        Ciclo ciclo2 = new Ciclo("Inregular");
        
        Usuario user = new Usuario("Aurora", "123");
        Usuario user2 = new Usuario("Lider", "a");
        Usuario user3 = new Usuario("Escravo", "b");
        
        Equipe eq = new Equipe("Aurora Aksnes", nivel1);
        Equipe eq2 = new Equipe("Marcos Torcate", nivel2);
        
        Pesquisador pesquisador = new Pesquisador("Aurora Aksnes", nivel1, eq,user);
        Pesquisador pesquisador2 = new Pesquisador("Marcos Torcate", nivel2, eq2,user2);
        Pesquisador pesquisador3 = new Pesquisador("Rubens Silva", nivel3, eq2,user3);
        
        //cadastrando
        UsuarioHibernate usuarioHibernate = new UsuarioHibernate();
        EquipeHibernate equipeHibernate = new EquipeHibernate();
        PesquisadorHibernate pesquisadorHibernate = new PesquisadorHibernate();
        NivelHibernate nivelHibernate = new NivelHibernate();
        CicloHibernate cicloHibernate = new CicloHibernate();
        
        cicloHibernate.insert(ciclo);
        cicloHibernate.insert(ciclo2);
        
        nivelHibernate.insert(nivel1);
        nivelHibernate.insert(nivel2);
        nivelHibernate.insert(nivel3);
        
        
        equipeHibernate.insert(eq);
        equipeHibernate.insert(eq2);
        
        usuarioHibernate.insert(user);
        usuarioHibernate.insert(user2);
        usuarioHibernate.insert(user3);
        
        pesquisadorHibernate.insert(pesquisador);
        pesquisadorHibernate.insert(pesquisador2);
        pesquisadorHibernate.insert(pesquisador3);
        
        
        
    }
}
