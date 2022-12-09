package com.example.vip.dto;



import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import jakarta.servlet.Registration;
import jakarta.servlet.ServletRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllVip {

    ArrayList<VipInfo2> vips;

    ArrayList<Dictionary> summary;

    ArrayList<Dictionary> summary2;



}
