package on.focus0147.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = User.FIND_WITH_DETAILS,
        query = "select distinct u from User u " +
                "left join fetch u.payments p " +
                "left join fetch u.financialInstruments f " +
                "where u.id = :id "
)
@NamedEntityGraph(name = User.FIND_BY_GRAPH,
        attributeNodes = {
                @NamedAttributeNode("payments"),
                @NamedAttributeNode("financialInstruments")
        }
)
@SqlResultSetMapping(name = User.USER_MAPPING,
        entities = {
        @EntityResult(entityClass = User.class,
                        fields = {
                                @FieldResult(name = "id", column = "USER_ID"),
                                @FieldResult(name = "name", column = "NAME"),
                                @FieldResult(name = "email", column = "EMAIL"),
                                @FieldResult(name = "encryptedPassword", column = "ENCRYPTED_PASSWORD")
                        }),
        @EntityResult(entityClass = Payment.class,
                        fields = {
                                @FieldResult(name = "id", column = "PAYMENT_ID"),
                                @FieldResult(name = "paymentDate", column = "PAYMENT_DATE"),
                                @FieldResult(name = "amount", column = "AMOUNT"),
                                @FieldResult(name = "currency", column = "CURRENCY"),
                                @FieldResult(name = "paymentType", column = "PAYMENT_TYPE")
                        })  ,
                @EntityResult(entityClass = FinancialInstrument.class,
                        fields = {
                                @FieldResult(name = "id", column = "FY_ID"),
                                @FieldResult(name = "name", column = "FY_NAME")
                        })
        }
)
@Entity
@Table(name="USERS")
public class User extends AbstractEntity{
    public static final String FIND_WITH_DETAILS = "User.findWithDetails";
    public static final String FIND_BY_GRAPH = "User.findByGraph";
    public static final String USER_MAPPING = "User.mapping";
    public static final String USER_MAPPING_QUERY = """
            select distinct
                u.NAME, u.EMAIL, u.ENCRYPTED_PASSWORD,
                p.ID AS PAYMENT_ID, p.PAYMENT_DATE, p.AMOUNT, p.CURRENCY, p.PAYMENT_TYPE,
                f.ID AS FY_ID, f.NAME AS FY_NAME,
                ufi.USER_ID
            from USERS u
                left join PAYMENTS p on p.USER_ID = u.ID
                left join USER_FINANCIAL_INSTRUMENT ufi on u.ID = ufi.USER_ID
                left join FINANCIAL_INSTRUMENT f on f.ID = ufi.FINANCIAL_INSTRUMENT_ID
            where u.id = :id
            """;

    @Serial
    private static final long serialVersionUID = 2L;

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "ENCRYPTED_PASSWORD", nullable = false)
    private String encryptedPassword;
    @OneToMany(mappedBy = "user", cascade= CascadeType.ALL, orphanRemoval=true)
    private Set<Payment> payments = new HashSet<>();
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

    public Set<Payment> getPayments() {
        return payments;
    }

    public Set<FinancialInstrument> getFinancialInstruments() {
        return financialInstruments;
    }

    public User withId(Integer id){
        this.id = id;
        return this;
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
        this.payments = payment;
        return this;
    }

    public User withFinancialInstruments(Set<FinancialInstrument> financialInstruments) {
        this.financialInstruments = financialInstruments;
        return this;
    }
}
