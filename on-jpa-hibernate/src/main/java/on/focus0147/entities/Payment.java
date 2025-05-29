package on.focus0147.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;
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
    private BigDecimal amount;
    @Column(name = "PAYMENT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(name = "CURRENCY", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    public User getUser() {
        return user;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public Payment withUser(User user) {
        this.user = user;
        return this;
    }

    public Payment withPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public Payment withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Payment withPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public Payment withCurrency(CurrencyType currency){
        this.currency = currency;
        return this;
    }
}
