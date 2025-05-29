package on.focus0147.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import on.focus0147.entities.FinancialInstrument;
import on.focus0147.msg.Constants;
import on.focus0147.repositories.FinancialInstrumentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Repository
public class FinancialInstrumentRepositoryImpl implements FinancialInstrumentRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public Optional<FinancialInstrument> findByIdWithUsers(Integer id) {
        Objects.requireNonNull(id, Constants.ERROR_ID);
        TypedQuery<FinancialInstrument> query =
                em.createNamedQuery(FinancialInstrument.FIND_WITH_USERS, FinancialInstrument.class)
                .setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException _) {
            return Optional.empty();
        }
    }
}
