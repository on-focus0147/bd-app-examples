package on.focus0147.entities;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USERS")
@NamedQuery(name="User.findWithDetails",
        query="select distinct u from User u " +
                "left join fetch u.payment p " +
                "left join fetch u.financialInstruments f " +
                "where u.id = :id "
)
public class User extends AbstractEntity{
    @Serial
    private static final long serialVersionUID = 2L;

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "ENCRYPTED_PASSWORD", nullable = false)
    private String encryptedPassword;
    @OneToMany(mappedBy = "user", cascade= CascadeType.ALL, orphanRemoval=true)
    private Set<Payment> payment;
    @ManyToMany
    @JoinTable(name = "USER_FINANCIAL_INSTRUMENT",
                joinColumns = @JoinColumn(name = "USER_ID"),
                inverseJoinColumns = @JoinColumn(name = "FINANCIAL_INSTRUMENT_ID"))
    private Set<FinancialInstrument> financialInstruments = new HashSet<>();

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    @Transactional
    public Set<Payment> getPayments() {
        return payment;
    }

    public Set<FinancialInstrument> getFinancialInstruments() {
        return financialInstruments;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    public User withEmail(String email) {
        this.email = email;
        return  this;
    }

    public User withPassword(String password){
        this.encryptedPassword = password;
        return this;
    }

    public User withPayments(Set<Payment> payment) {
        this.payment = payment;
        return this;
    }

    public User withFinancialInstruments(Set<FinancialInstrument> financialInstruments) {
        this.financialInstruments = financialInstruments;
        return this;
    }
}
