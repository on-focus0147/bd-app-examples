package on.focus0147.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Set;

@Entity
@Table(name = "FINANCIAL_INSTRUMENT")
@NamedQuery(name = "FinancialInstrument.findWithUsers",
            query = "select distinct f from FinancialInstrument f " +
                    "left join fetch f.users u " +
                    "where f.id = :id")
public class FinancialInstrument extends AbstractEntity{
    @Serial
    private static final long serialVersionUID = 4L;
    @Column(name = "name", nullable = false)
    String name;
    //@ManyToMany(mappedBy = "financialInstruments")
    @ManyToMany
    @JoinTable(name = "USER_FINANCIAL_INSTRUMENT",
            joinColumns = @JoinColumn(name = "FINANCIAL_INSTRUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
