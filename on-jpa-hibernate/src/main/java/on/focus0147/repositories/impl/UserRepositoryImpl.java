package on.focus0147.repositories.impl;

import on.focus0147.repositories.UserRepository;
import on.focus0147.entities.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component("userRepository")
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    UserRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User", User.class).list();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Integer id) {
        User user = sessionFactory
                .getCurrentSession()
                .createQuery("FROM User WHERE id = :userId", User.class)
                .setParameter("userId", id)
                .uniqueResult();
        return (user == null) ? Optional.empty() : Optional.of(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        User user = sessionFactory
                .getCurrentSession()
                .createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult();
        return (user == null) ? Optional.empty() : Optional.of(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findWithDependenciesByHibernate(Integer id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        Hibernate.initialize(user.getPayments());
        Hibernate.initialize(user.getFinancialInstruments());
        return Optional.of(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findWithDependenciesByQuery(Integer id) {
        User user = sessionFactory.getCurrentSession()
                .createNamedQuery("User.findWithDetails", User.class)
                .setParameter("id", id).uniqueResult();
        return (user == null) ? Optional.empty() : Optional.of(user);
    }

    @Transactional
    @Override
    public User add(User user) {
        var session = sessionFactory.getCurrentSession();
        if (user.getId() == null) {
            session.persist(user);
        } else {
            session.merge(user);
        }
        return user;
    }

    @Transactional
    @Override
    public User update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
        return user;
    }

    @Transactional
    @Override
    public void dropById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.remove(user);
    }
}
