/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Irandir
 */
public class FabricaDeConexao {
    private SessionFactory factory;
    private Session session;
    
    public Session novaConexao(){
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        session.beginTransaction();
        return session;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
}
