package com.codegym.model.student;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "class")
@Data
public class ClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
}
