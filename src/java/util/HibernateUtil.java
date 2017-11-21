/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Herikles
 */
public class HibernateUtil {

    //private static HibernateUtil instance;
    private static SessionFactory sessionFactory;

    private HibernateUtil() {

    }

    public static Session getSession() {
        /*if (instance == null) {
            instance = new HibernateUtil();
        }*/
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();

        }
        return sessionFactory.openSession();
    }
}
