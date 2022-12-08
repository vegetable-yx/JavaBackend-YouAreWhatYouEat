package com.example.ingredient.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIngRecordItem {
    int recordId;
    int ingId;
    String ingName;
    String purchaseDate;
    String measureUnit;
}
