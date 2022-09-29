package app.model.order.receipt.receiptDetail;

import app.model.order.address.Address;
import app.model.order.receipt.Receipt;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "receipt_detail")
@Getter
@Setter
@RequiredArgsConstructor
public class ReceiptDetail implements IReceiptDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipt receipt;

    @Valid
    @Embedded
    private Address address;

    @Future
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Getter(onMethod_ = @NotNull(message = "Выберите дату"))
    @Column(name = "date")
    private Date date;

}
