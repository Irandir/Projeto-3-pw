/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernates;

import java.util.ArrayList;
import java.util.List;
import model.DAO.interfaces.PesquisadorDAO;
import model.pojo.Equipe;
import model.pojo.Nivel;
import model.pojo.Pesquisador;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class PesquisadorHibernate implements PesquisadorDAO {

    @Override
    public void insert(Pesquisador o) {
        Session session = HibernateUtil.getSession();
        NivelHibernate nH = new NivelHibernate();
        EquipeHibernate eH = new EquipeHibernate();
        try {
            session.beginTransaction();
            List<Nivel> n = nH.recuperarPorNome(o.getNivel().getNivel());
            if (n.isEmpty()) {
                nH.insert(o.getNivel());
            } else {
                o.setNivel(n.get(0));
            }
            List<Equipe> e = eH.recuperarPorNome(o.getEquipe().getNome());
            if (e.isEmpty()) {
                eH.insert(o.getEquipe());
            } else {
                o.setEquipe(e.get(0));
            }
            
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Pesquisador. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Pesquisador o) {
        Session session = HibernateUtil.getSession();
        NivelHibernate nH = new NivelHibernate();
        EquipeHibernate eH = new EquipeHibernate();
        try {
            session.beginTransaction();
            List<Nivel> n = nH.recuperarPorNome(o.getNivel().getNivel());
            if (n.isEmpty()) {
                nH.insert(o.getNivel());
            } else {
                o.setNivel(n.get(0));
            }
            List<Equipe> e = eH.recuperarPorNome(o.getEquipe().getNome());
            if (e.isEmpty()) {
                eH.insert(o.getEquipe());
            } else {
                o.setEquipe(e.get(0));
            }
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Pesquisador. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Pesquisador o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Pesquisador. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Pesquisador read(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Pesquisador) session.get(Pesquisador.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Pesquisador> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Pesquisador> lista = session.createQuery("from " + Pesquisador.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Pesquisadores. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Pesquisador> recuperarPorNome(String nome) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Pesquisador> listaAux = session.createQuery("from " + Pesquisador.class.getName()).list();
            List<Pesquisador> lista = new ArrayList<>();
            for (Pesquisador Pesquisador : listaAux) {
                if (Pesquisador.getNome().equals(nome)) {
                    lista.add(Pesquisador);
                }
            }
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Pesquisadores. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }
}
