package com.example.orderdish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishComment {
    @JsonProperty("comment_content")
    private String comment_content;

    @JsonProperty("comment_time")
    private String comment_time;

    @JsonProperty("comment_star")
    private Double comment_star;
}
