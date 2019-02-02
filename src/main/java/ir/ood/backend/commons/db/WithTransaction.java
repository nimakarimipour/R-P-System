package ir.ood.backend.commons.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;

public class WithTransaction {

    private SessionFactory sessionFactory;

    public WithTransaction(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    public void run(Runnable runnable) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ManagedSessionContext.bind(session);
        try {
            runnable.run();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        session.flush();
        transaction.commit();
        session.close();
    }

}
