package util.hibernate;
/**
 *
 * @author deividnn
 */
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

@Interceptor
@Transactional
public class TransactionManager {
    @Inject
    private HibernateSF sessionFactory;

    @AroundInvoke
    public Object handleTransaction(InvocationContext context) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            return context.proceed();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (tx != null) {
                if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
                    try {
                        tx.commit();
                    } catch (Exception e) {
                        tx.rollback();
                        throw e;
                    }
                }
            }
        }
    }
}
