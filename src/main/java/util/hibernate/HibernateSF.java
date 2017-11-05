package util.hibernate;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    @PreDestroy
    private void closeSession() {
        if (currentSession != null && currentSession.isOpen()) {
            currentSession.close();
        }
    }
}
