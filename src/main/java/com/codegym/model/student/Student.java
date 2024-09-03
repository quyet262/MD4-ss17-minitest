package com.codegym.model.student;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private Integer mark;
    private String image;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassStudent classStudent;

    public Student() {
    }

    public Student(String firstName, String lastName, String dob, String address, Integer mark, ClassStudent classStudent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.mark = mark;
        this.classStudent = classStudent;
    }
}
