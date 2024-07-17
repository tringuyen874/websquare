package com.example.websquare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday", nullable = true)
    private Date birthday;

    @Column(name = "gender")
    private String gender; // 1:men

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "team")
    private String team;

    @Column(name = "status")
    private boolean status; // 1:active

    @Column(name = "action")
    private String action;

    // private String checked;
    public User(String name, Date birthday, String gender, Integer phone, String email, String address, String team, boolean b) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.team = team;
        this.status = b;

    }
}
