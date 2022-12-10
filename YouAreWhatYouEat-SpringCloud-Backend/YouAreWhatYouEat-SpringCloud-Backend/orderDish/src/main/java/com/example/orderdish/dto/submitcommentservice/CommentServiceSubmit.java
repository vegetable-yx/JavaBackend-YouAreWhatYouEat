package com.example.orderdish.dto.submitcommentservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentServiceSubmit {
    private Double rate;
    private String content;
    private String username;
}
