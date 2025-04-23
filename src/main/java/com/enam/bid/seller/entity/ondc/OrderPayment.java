package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_payment", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPayment extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "collected_by", length = 50)
    private String collectedBy;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "params_amount", precision = 15, scale = 2)
    private BigDecimal paramsAmount;

    @Column(name = "params_currency", length = 10)
    private String paramsCurrency;

    @Column(name = "params_bank_account_number", length = 50)
    private String paramsBankAccountNumber;

    @Column(name = "params_virtual_payment_address", length = 255)
    private String paramsVirtualPaymentAddress;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "type", length = 50)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    private PaymentType paymentType;
}
