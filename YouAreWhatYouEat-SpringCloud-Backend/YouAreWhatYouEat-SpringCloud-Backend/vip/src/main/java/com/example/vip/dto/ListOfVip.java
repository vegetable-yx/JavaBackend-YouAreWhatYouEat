package com.example.vip.dto;

import jakarta.servlet.Registration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Dictionary;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfVip {
    Dictionary<String, ArrayList<String>> xaxis;
}
