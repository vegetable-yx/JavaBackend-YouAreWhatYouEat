package com.example.orderlists.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Dictionary;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrders {

    int errorCode;

    List<Order> orders;

    List<Dictionary> summary;
}
