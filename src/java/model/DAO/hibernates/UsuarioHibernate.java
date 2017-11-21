/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernates;

import java.util.ArrayList;
import java.util.List;
import model.DAO.interfaces.UsuarioDAO;
import model.pojo.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class UsuarioHibernate implements UsuarioDAO {

    @Override
    public void insert(Usuario o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Usuario. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Usuario o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Usuario. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Usuario o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Usuario. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Usuario read(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Usuario) session.get(Usuario.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Usuario> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Usuario> lista = session.createQuery("from " + Usuario.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os NIVEIS. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Usuario> recuperarPorNome(String nome) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Usuario> listaAux = session.createQuery("from " + Usuario.class.getName()).list();
            List<Usuario> lista = new ArrayList<>();
            for (Usuario Usuario : listaAux) {
                if (Usuario.getLogin().equals(nome)) {
                    lista.add(Usuario);
                }
            }
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Usuarios. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

}
