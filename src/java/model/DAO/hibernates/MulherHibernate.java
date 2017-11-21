/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernates;

import java.util.ArrayList;
import java.util.List;
import model.DAO.interfaces.MulherDAO;
import model.pojo.Ciclo;
import model.pojo.Mulher;
import model.pojo.Pesquisador;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class MulherHibernate implements MulherDAO {

    @Override
    public void insert(Mulher o) {
        Session session = HibernateUtil.getSession();
        CicloHibernate cH = new CicloHibernate();
        PesquisadorHibernate pH = new PesquisadorHibernate();
        try {
            session.beginTransaction();
            List<Ciclo> c = cH.recuperarPorNome(o.getTipo().getTipo());
            if (c.isEmpty()) {
                cH.insert(o.getTipo());
            } else {
                o.setTipo(c.get(0));
            }
            List<Pesquisador> p = pH.recuperarPorNome(o.getPesquisador().getNome());
            if(p.isEmpty()) {
                pH.insert(o.getPesquisador());
            } else {
                o.setPesquisador(p.get(0));
            }
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Mulher. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Mulher o) {
        Session session = HibernateUtil.getSession();

        CicloHibernate cH = new CicloHibernate();
        PesquisadorHibernate pH = new PesquisadorHibernate();
        try {
            session.beginTransaction();
            List<Ciclo> c = cH.recuperarPorNome(o.getTipo().getTipo());
            if (c.isEmpty()) {
                cH.insert(o.getTipo());
            } else {
                o.setTipo(c.get(0));
            }
            List<Pesquisador> p = pH.recuperarPorNome(o.getPesquisador().getNome());
            if(p.isEmpty()) {
                pH.insert(o.getPesquisador());
            } else {
                o.setPesquisador(p.get(0));
            }
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Mulher. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Mulher o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Mulher. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Mulher read(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Mulher) session.get(Mulher.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Mulher. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Mulher> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Mulher> lista = session.createQuery("from " + Mulher.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os mulheres. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Mulher> recuperarPorNome(String nome) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Mulher> listaAux = session.createQuery("from " + Mulher.class.getName()).list();
            List<Mulher> lista = new ArrayList<>();
            for (Mulher Mulher : listaAux) {
                if (Mulher.getTipo().equals(nome)) {
                    lista.add(Mulher);
                }
            }
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Mulheres. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }
}
