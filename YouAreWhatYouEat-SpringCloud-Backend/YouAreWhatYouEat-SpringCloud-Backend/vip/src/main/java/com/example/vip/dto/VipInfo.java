package com.example.vip.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipInfo {
    String user_name;

    String gender;

    int balance;

    BigInteger credit;
}
