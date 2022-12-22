package com.shop.orderserver.model.receipt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "receipt")
@Getter
@Setter
@RequiredArgsConstructor
public class Receipt implements IReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter(onMethod_ = @NotNull(message = "Выберите способ получения"))
    @Column(name = "receipt_type")
    private String type;

}
