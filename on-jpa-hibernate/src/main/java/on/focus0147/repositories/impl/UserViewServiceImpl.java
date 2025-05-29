package on.focus0147.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import on.focus0147.repositories.UserViewService;
import on.focus0147.view.UserUsdView;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserViewServiceImpl implements UserViewService {

    private static final String ALL_USERS_WITH_USD ="""
            select distinct new on.focus0147.view.UserUsdView(u.email) from User u
            left join u.payments p
            where p.currency="USD"
            """;
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<UserUsdView> findAll() {
        return em.createQuery(ALL_USERS_WITH_USD, UserUsdView.class).getResultList();
    }
}
