package com.shop.orderserver.model.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "payment")
@Getter
@Setter
@RequiredArgsConstructor
public class Payment implements IPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter(onMethod_ = @NotNull(message = "Выберите способ оплаты"))
    @Column(name = "payment_type")
    private String type;

    @Column(name = "description")
    private String description;

}
