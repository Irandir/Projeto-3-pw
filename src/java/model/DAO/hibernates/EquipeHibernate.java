/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernates;

import java.util.ArrayList;
import java.util.List;
import model.DAO.interfaces.EquipeDAO;
import model.pojo.Equipe;
import model.pojo.Nivel;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class EquipeHibernate implements EquipeDAO {

    @Override
    public void insert(Equipe o) {
        Session session = HibernateUtil.getSession();
        NivelHibernate nH = new NivelHibernate();

        try {
            session.beginTransaction();
            List<Nivel> n = nH.recuperarPorNome(o.getNivel().getNivel());
            if (n.isEmpty()) {
                nH.insert(o.getNivel());
            } else {
                o.setNivel(n.get(0));
            }
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Equipe. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Equipe o) {
        Session session = HibernateUtil.getSession();
        NivelHibernate nH = new NivelHibernate();
        try {
            session.beginTransaction();

            List<Nivel> n = nH.recuperarPorNome(o.getNivel().getNivel());
            if (n.isEmpty()) {
                nH.insert(o.getNivel());
            } else {
                o.setNivel(n.get(0));
            }
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Equipe. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Equipe o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Equipe. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Equipe read(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Equipe) session.get(Equipe.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Equipe> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Equipe> lista = session.createQuery("from " + Equipe.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Equipes. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Equipe> recuperarPorNome(String nome) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Equipe> listaAux = session.createQuery("from " + Equipe.class.getName()).list();
            List<Equipe> lista = new ArrayList<>();
            for (Equipe Equipe : listaAux) {
                if (Equipe.getNome().equals(nome)) {
                    lista.add(Equipe);
                }
            }
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Equipes. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }
}
