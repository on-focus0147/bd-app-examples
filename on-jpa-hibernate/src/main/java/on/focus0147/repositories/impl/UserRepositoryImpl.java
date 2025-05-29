package on.focus0147.repositories.impl;

import jakarta.persistence.*;
import on.focus0147.entities.FinancialInstrument;
import on.focus0147.entities.Payment;
import on.focus0147.entities.User;
import on.focus0147.msg.Constants;
import on.focus0147.repositories.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String FIND_ALL_QUERY = "FROM User";
    private static final String FIND_BY_ID_QUERY = "FROM User WHERE id = :userId";
    private static final String FIND_BY_EMAIL_QUERY = "FROM User WHERE email = :email";
    private static final String FIND_BY_ID_NATIVE_QUERY = "SELECT * FROM USERS WHERE ID = ?";


    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return em.createQuery(FIND_ALL_QUERY, User.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Integer id) {
        Objects.requireNonNull(id, Constants.ERROR_ID);
        TypedQuery<User> query = em.createQuery(FIND_BY_ID_QUERY, User.class)
                .setParameter("userId", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException _) {
        return Optional.empty();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByIdNativeQuery(Integer id) {
        Objects.requireNonNull(id, Constants.ERROR_ID);
        Query query = em.createNativeQuery(FIND_BY_ID_NATIVE_QUERY, User.class)
                .setParameter(1, id);
        try {
            return Optional.of((User) query.getSingleResult());
        } catch (NoResultException _) {
            return Optional.empty();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        Objects.requireNonNull(email, Constants.ERROR_EMAIL);
        TypedQuery<User> query = em.createQuery(FIND_BY_EMAIL_QUERY, User.class)
                .setParameter("email", email);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException _) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findWithDependenciesByEntityGraph(Integer id) {
        Objects.requireNonNull(id, Constants.ERROR_ID);
        EntityGraph<?> graph = em.createEntityGraph(User.FIND_BY_GRAPH);
        Map<String, Object> hints = Collections.singletonMap(
                "jakarta.persistence.fetchgraph", graph
        );
        User user = em.find(User.class, id, hints);
        return (user == null) ? Optional.empty() : Optional.of(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEntityManager(Integer id) {
        Objects.requireNonNull(id, Constants.ERROR_ID);
        User user = em.find(User.class, id);
        return (user == null) ? Optional.empty() : Optional.of(user);
    }

    @Override
    public Optional<User> findByMapping(Integer id) {
        Objects.requireNonNull(id, Constants.ERROR_ID);
        Query query = em.createNativeQuery(User.USER_MAPPING_QUERY, User.USER_MAPPING)
                .setParameter("id", id);
        List<Object[]> results = query.getResultList();
        //берем первого пользователя
        return Arrays.stream(results.getFirst())
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .findFirst()
                //получаем связанные сущности
                .map(user -> {
                    var payments = new HashSet<Payment>();
                    var instruments = new HashSet<FinancialInstrument>();

                    results.forEach(row -> {
                        if (row[1] instanceof Payment payment) {
                            payments.add(payment);
                        }
                        if (row[2] instanceof FinancialInstrument instrument) {
                            instruments.add(instrument);
                        }
                    });

                    return user.withPayments(payments)
                            .withFinancialInstruments(instruments);
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findWithDependenciesByQuery(Integer id) {
        Objects.requireNonNull(id, Constants.ERROR_ID);
        TypedQuery<User> query = em.createNamedQuery(User.FIND_WITH_DETAILS, User.class)
                .setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException _) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public User add(User user) {
        Objects.requireNonNull(user, Constants.ERROR_USER);
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    @Transactional
    @Override
    public User update(User user) {
        Objects.requireNonNull(user, Constants.ERROR_USER);
        em.merge(user);
        return user;
    }

    @Transactional
    @Override
    public void dropById(Integer id) {
        Objects.requireNonNull(id, Constants.ERROR_ID);
        User user = em.find(User.class, id);
        if(user != null){
            em.remove(user);
        }
    }
}
