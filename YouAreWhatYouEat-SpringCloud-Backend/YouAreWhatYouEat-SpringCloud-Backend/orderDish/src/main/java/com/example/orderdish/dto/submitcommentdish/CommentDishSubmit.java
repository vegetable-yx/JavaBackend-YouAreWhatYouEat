package com.example.orderdish.dto.submitcommentdish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDishSubmit {
    private Double rate;
    private String content;
    private BigInteger dish_id;
    private String username;
}
