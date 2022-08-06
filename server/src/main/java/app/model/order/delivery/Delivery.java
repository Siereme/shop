package app.model.order.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "delivery")
@Getter
@Setter
@RequiredArgsConstructor
public class Delivery implements IDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "building")
    private String building;

    @Column(name = "date")
    private Date date;

}
