package com.example.ingredient.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIngRecord {
    List<GetIngRecordItem> data;
    int total;
}
