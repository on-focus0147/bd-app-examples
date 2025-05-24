package on.focus0147.repositories.impl;

import on.focus0147.configuration.HibernateConfiguration;
import on.focus0147.configuration.TestContainersBase;
import on.focus0147.entities.Payment;
import on.focus0147.entities.User;
import on.focus0147.repositories.PaymentRepository;
import on.focus0147.repositories.UserRepository;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Testcontainers
@SpringJUnitConfig(HibernateConfiguration.class)
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
        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        Assertions.assertEquals("Alex Smith", user.getName());
        Assertions.assertEquals("alex.smith@example.com", user.getEmail());
        Assertions.assertEquals("+zmKNX2kafRK6M2VZNxM+cVPCZfxQJ6r", user.getEncryptedPassword());
        Set<Payment> paymentSet = user.getPayments();
        Assertions.assertNotNull(paymentSet);//PersistentBag
        Assertions.assertThrows(LazyInitializationException.class, paymentSet::size);
    }

    @Test
    void testFindByEmail(){
        Optional<User> optionalUser = userRepository.findByEmail("alex.smith@example.com");
        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Alex Smith", user.getName());
        Assertions.assertEquals("alex.smith@example.com", user.getEmail());
        Assertions.assertEquals("+zmKNX2kafRK6M2VZNxM+cVPCZfxQJ6r", user.getEncryptedPassword());
        //LAZY Load
        Set<Payment> paymentSet = user.getPayments();
        Assertions.assertNotNull(paymentSet);//PersistentBag
        Assertions.assertThrows(LazyInitializationException.class, paymentSet::size);
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
    void testFindWithDependenciesByQuery(){
        Optional<User> optionalUser = userRepository.findWithDependenciesByQuery(1);
        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        Assertions.assertEquals("Alex Smith", user.getName());
        Assertions.assertEquals("alex.smith@example.com", user.getEmail());
        Assertions.assertEquals("+zmKNX2kafRK6M2VZNxM+cVPCZfxQJ6r", user.getEncryptedPassword());
        Assertions.assertNotNull(user.getPayments());
        Assertions.assertEquals(2 , user.getPayments().size());
        Assertions.assertNotNull(user.getFinancialInstruments());
        Assertions.assertEquals(2, user.getFinancialInstruments().size());
    }

    @Test
    void testFindWithDependenciesByHibernate(){
        Optional<User> optionalUser = userRepository.findWithDependenciesByHibernate(1);
        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        Assertions.assertEquals("Alex Smith", user.getName());
        Assertions.assertEquals("alex.smith@example.com", user.getEmail());
        Assertions.assertEquals("+zmKNX2kafRK6M2VZNxM+cVPCZfxQJ6r", user.getEncryptedPassword());
        Assertions.assertNotNull(user.getPayments());
        Assertions.assertEquals(2 , user.getPayments().size());
        Assertions.assertNotNull(user.getFinancialInstruments());
        Assertions.assertEquals(2, user.getFinancialInstruments().size());
    }
}