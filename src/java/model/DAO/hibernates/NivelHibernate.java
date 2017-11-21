/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernates;

import java.util.ArrayList;
import java.util.List;
import model.DAO.interfaces.NivelDAO;
import model.pojo.Nivel;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class NivelHibernate implements NivelDAO {
    @Override
    public void insert(Nivel o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Nivel. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Nivel o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Nivel. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Nivel o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Nivel. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Nivel read(Integer id) {
         Session session = HibernateUtil.getSession();
        try {
            return (Nivel) session.get(Nivel.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;}

    @Override
    public List<Nivel> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Nivel> lista = session.createQuery("from " + Nivel.class.getName()).list();
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
    public List<Nivel> recuperarPorNome(String nome) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Nivel> listaAux = session.createQuery("from " + Nivel.class.getName()).list();
            List<Nivel> lista = new ArrayList<>();
            for (Nivel Nivel : listaAux) {
                if(Nivel.getNivel().equals(nome)){
                    lista.add(Nivel);
                }
            }
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Niveis. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

   

}
