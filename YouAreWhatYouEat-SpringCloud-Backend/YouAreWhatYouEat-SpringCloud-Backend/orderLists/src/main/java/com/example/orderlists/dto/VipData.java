package com.example.orderlists.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipData {
    String user_name;
    String gender;
    String avatar;
    List<VipOrder> orders;
    int order_number;
    int total_credit;
}
