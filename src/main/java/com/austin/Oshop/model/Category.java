package com.austin.Oshop.model;

import lombok.*;

import javax.persistence.*;
import java.io.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 12/Oct/2022 - 12:09 AM
 * @project Oshop
 */
@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


}
