package on.focus0147.repositories.impl;

import on.focus0147.entities.FinancialInstrument;
import on.focus0147.repositories.FinancialInstrumentRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("finRepository")
public class FinancialInstrumentRepositoryImpl implements FinancialInstrumentRepository {

    private final SessionFactory sessionFactory;

    FinancialInstrumentRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public FinancialInstrument findByIdWithUsers(Integer id) {
        return sessionFactory.getCurrentSession()
                .createNamedQuery("FinancialInstrument.findWithUsers", FinancialInstrument.class)
                .setParameter("id", id)
                .uniqueResult();
    }
}
