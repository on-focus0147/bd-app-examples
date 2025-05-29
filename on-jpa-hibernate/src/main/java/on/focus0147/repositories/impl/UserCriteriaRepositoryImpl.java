package on.focus0147.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import on.focus0147.entities.AbstractEntity_;
import on.focus0147.entities.User;
import on.focus0147.entities.User_;
import on.focus0147.repositories.UserCriteriaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCriteriaRepositoryImpl implements UserCriteriaRepository {

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<User> getFilteredUsers(String name, String email, String sortBy, boolean ascending) {
        if ((name == null || name.isBlank()) &&
                (email == null || email.isBlank()) &&
                sortBy == null) {
            return List.of();
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);

        Predicate predicate = cb.conjunction();
        if (name != null && !name.isBlank()) {
            predicate = cb.and(predicate, cb.equal(user.get(User_.name), name));
        }
        if (email != null && !email.isBlank()) {
            predicate = cb.and(predicate, cb.equal(user.get(User_.email), email));
        }

        cq.where(predicate);

        if (sortBy != null) {
            Path<?> sortField = switch (sortBy) {
                case "id" -> user.get(AbstractEntity_.id);
                case "name" -> user.get(User_.name);
                case "email" -> user.get(User_.email);
                case "encryptedPassword" -> user.get(User_.encryptedPassword);
                default -> throw new IllegalArgumentException("Unknown sortBy field: " + sortBy);
            };

            cq.orderBy(ascending ? cb.asc(sortField) : cb.desc(sortField));
        }

        cq.distinct(true);
        user.fetch(User_.payments.getName(), JoinType.LEFT);
        user.fetch(User_.financialInstruments.getName(), JoinType.LEFT);

        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }

}
