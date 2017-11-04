/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hibernate;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

/**
 *
 * @author deividnn
 */
@RequestScoped
public class HibernateSF {

    @Inject
    private SessionFactory sessionFactory;

    private Session currentSession;

    public Session getCurrentSession() {
        if (currentSession == null) {
            currentSession = sessionFactory.openSession();
        }
        return currentSession;
    }

    public StatelessSession openStatelessSession() {
        return sessionFactory.openStatelessSession();
    }

    @PreDestroy
    private void closeSession() {
        if (currentSession != null && currentSession.isOpen()) {
            currentSession.close();
        }
    }
}
