package com.example.vip.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutUpdateVip {
    @JsonProperty("user_name")
    String userName;
    @JsonProperty("gender")
    String gender;
    @JsonProperty("birthday")
    String birthday;
    @JsonProperty("credit")
    BigInteger credit;
    @JsonProperty("balance")
    int balance;
}
