package on.focus0147.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import on.focus0147.entities.Payment;
import on.focus0147.msg.Constants;
import on.focus0147.repositories.PaymentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public Payment getById(Integer id) {
        Objects.requireNonNull(id,  Constants.ERROR_ID);
        return em.find(Payment.class, id);
    }
}
