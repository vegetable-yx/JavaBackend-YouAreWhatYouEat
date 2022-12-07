package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class OrderSummary {
    private BigInteger order_count;
    private BigInteger awating_count;
    private Double awating_credit;
    private BigInteger processing_count;
    private Double processing_credit;
    private BigInteger completed_count;
    private Double completed_credit;
    private BigInteger payed_count;
    private Double payed_credit;
    private Double total_credit;
    private Double today_credit;

    public OrderSummary()
    {
        this.order_count=BigInteger.valueOf(0);
        this.awating_count=BigInteger.valueOf(0);
        this.awating_credit=0d;
        this.processing_count=BigInteger.valueOf(0);
        this.processing_credit=0d;
        this.completed_count=BigInteger.valueOf(0);
        this.completed_credit=0d;
        this.payed_count=BigInteger.valueOf(0);
        this.payed_credit=0d;
        this.total_credit=0d;
        this.today_credit=0d;
    }
}
