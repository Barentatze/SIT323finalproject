package com.reins.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String author;
    @Column(columnDefinition = "TEXT")
    String description;
    Integer price;
    String cover;
    Integer sales;

    public Book(Long id) {
        this.id = id;
    }
}
