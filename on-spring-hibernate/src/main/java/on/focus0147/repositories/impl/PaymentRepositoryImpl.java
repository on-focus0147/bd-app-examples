package on.focus0147.repositories.impl;

import on.focus0147.entities.Payment;
import on.focus0147.repositories.PaymentRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("payRepository")
public class PaymentRepositoryImpl implements PaymentRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    PaymentRepositoryImpl(SessionFactory sessionFactory){
    this.sessionFactory = sessionFactory;
    }

    @Override
    public Payment getById(Integer id) {
        return sessionFactory.getCurrentSession().get(Payment.class, id);
    }
}
