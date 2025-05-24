package on.focus0147.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Date;

@Entity
@Table(name="PAYMENTS")
public class Payment extends AbstractEntity{
    @Serial
    private static final long serialVersionUID = 3L;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @Column(name = "PAYMENT_DATE", nullable = false)
    private Date paymentDate;
    @Column(name = "AMOUNT", nullable = false)
    private Long amount;
    @Column(name = "PAYMENT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public User getUser() {
        return user;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public Long getAmount() {
        return amount;
    }

    public PaymentType getTransactionType() {
        return paymentType;
    }

    public Payment withUser(User user) {
        this.user = user;
        return this;
    }

    public Payment withPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public Payment withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Payment withPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }
}
