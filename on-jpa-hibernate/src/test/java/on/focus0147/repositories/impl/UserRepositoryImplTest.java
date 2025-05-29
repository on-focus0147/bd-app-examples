package on.focus0147.repositories.impl;

import on.focus0147.configuration.ApplicationConfiguration;
import on.focus0147.configuration.TestContainersBase;
import on.focus0147.entities.Payment;
import on.focus0147.entities.User;
import on.focus0147.repositories.PaymentRepository;
import on.focus0147.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

@Testcontainers
@SpringJUnitConfig(ApplicationConfiguration.class)
class UserRepositoryImplTest extends TestContainersBase {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentRepository payRepository;

    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(10, users.size());
    }

    @Test
    void testFindById() {
        Optional<User> optionalUser = userRepository.findById(1);
        checkLazyAlex(optionalUser);
    }

    @Test
    void testFindByIdNotExist() {
        Optional<User> optionalUser = userRepository.findById(100);
        Assertions.assertFalse(optionalUser.isPresent());
    }

    @Test
    void testFindByIdNull() {
        Assertions.assertThrows(NullPointerException.class, ()->userRepository.findById(null));
    }

    @Test
    void testFindByEmail(){
        Optional<User> optionalUser = userRepository.findByEmail("alex.smith@example.com");
        checkLazyAlex(optionalUser);
    }

    @Test
    void testFindByEmailNull(){
        Assertions.assertThrows(NullPointerException.class, ()->userRepository.findByEmail(null));
    }

    @Test
    @Transactional //автоматически откатывает результат теста
    void testAdd() {
        User user = new User()
                .withName("Addy")
                .withEmail("addy@mail.ru")
                .withPassword("addyPass");
        user = userRepository.add(user);
        Assertions.assertNotNull(user.getId());
    }

    @Test
    void testAddNull() {
        Assertions.assertThrows(NullPointerException.class, ()->userRepository.add(null));
    }

    @Test
    @Transactional
    void testUpdate() {
        String newName = "New User";
        Optional<User> optionalUser = userRepository.findById(1);
        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get().withName(newName);
        userRepository.update(user);

        Optional<User> optionalNewUser = userRepository.findById(1);
        Assertions.assertTrue(optionalNewUser.isPresent());
        User newUser = optionalNewUser.get();
        Assertions.assertEquals(newName, newUser.getName());
    }

    @Test
    void testUpdateNull() {
        Assertions.assertThrows(NullPointerException.class, ()->userRepository.update(null));
    }

    @Test
    @Transactional
    void testDropById() {
        int userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertTrue(optionalUser.isPresent());

       userRepository.dropById(userId);
        Optional<User> optionalDroppedUser = userRepository.findById(userId);
        Assertions.assertFalse(optionalDroppedUser.isPresent());
        Payment p1 = payRepository.getById(1);
        Assertions.assertNull(p1);
        Payment p2 = payRepository.getById(2);
        Assertions.assertNull(p2);
    }

    @Test
    void testDropByIdNull() {
        Assertions.assertThrows(NullPointerException.class, ()-> userRepository.dropById(null));
    }

    @Test
    void findByIdNativeQuery() {
        Optional<User> optionalUser = userRepository.findByIdNativeQuery(1);
        checkLazyAlex(optionalUser);
    }

    @Test
    void findByIdNativeQueryNull() {
        Assertions.assertThrows(NullPointerException.class, ()-> userRepository.findByIdNativeQuery(null));
    }

    @Test
    void findWithDependenciesByEntityGraph() {
        Optional<User> optionalUser = userRepository.findWithDependenciesByEntityGraph(1);
        checkFullAlex(optionalUser);
    }

    @Test
    void findWithDependenciesByEntityGraphNull() {
        Assertions.assertThrows(NullPointerException.class,
                ()-> userRepository.findWithDependenciesByEntityGraph(null));
    }

    @Test
    void findByEntityManager() {
        Optional<User> optionalUser = userRepository.findByEntityManager(1);
        checkLazyAlex(optionalUser);
    }

    @Test
    void findByEntityManagerNull() {
        Assertions.assertThrows(NullPointerException.class,
                ()->  userRepository.findByEntityManager(null));
    }

    @Test
    void findWithDependenciesByQuery() {
        Optional<User> optionalUser = userRepository.findWithDependenciesByQuery(1);
        checkFullAlex(optionalUser);
    }

    @Test
    void findWithDependenciesByQueryNull() {
        Assertions.assertThrows(NullPointerException.class,
                ()->  userRepository.findWithDependenciesByQuery(null));
    }

    @Test
    void findByMapping() {
        Optional<User> optionalUser = userRepository.findByMapping(1);
        checkFullAlex(optionalUser);
    }

    @Test
    void findByMappingNull() {
        Assertions.assertThrows(NullPointerException.class,
                ()->  userRepository.findByMapping(null));
    }
}