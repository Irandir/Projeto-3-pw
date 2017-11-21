/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernates;

import java.util.ArrayList;
import java.util.List;
import model.DAO.interfaces.CicloDAO;
import model.pojo.Ciclo;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class CicloHibernate implements CicloDAO {

    @Override
    public void insert(Ciclo o) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Ciclo. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Ciclo o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Ciclo. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Ciclo o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Ciclo. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Ciclo read(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Ciclo) session.get(Ciclo.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Ciclo> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Ciclo> lista = session.createQuery("from " + Ciclo.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Ciclos. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Ciclo> recuperarPorNome(String nome) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            //List<Ciclo> lista = session.createQuery("from " + Ciclo.class.getName()+" c where c.tipo LIKE :"+nome).getResultList();
            List<Ciclo> listaAux = session.createQuery("from " + Ciclo.class.getName()).list();
            List<Ciclo> lista = new ArrayList<>();
            for (Ciclo ciclo : listaAux) {
                if(ciclo.getTipo().equals(nome)){
                    lista.add(ciclo);
                }
            }
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Ciclos. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }
}
