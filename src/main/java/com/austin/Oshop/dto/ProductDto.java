package com.austin.Oshop.dto;

import lombok.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 14/Oct/2022 - 1:49 AM
 * @project Oshop
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean active;
    private String category;


}
