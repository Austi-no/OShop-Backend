package com.austin.Oshop.model;

import lombok.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 14/Oct/2022 - 1:29 AM
 * @project Oshop
 */
@Table(name = "product")
@Entity
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private Boolean active;



    @Column(name = "date_created")
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {
    }

    public Product(
            String name, String description, Double price, String imageUrl,
            Boolean active, Date dateCreated, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.active = active;
        this.dateCreated = dateCreated;
        this.category = category;
    }
}
