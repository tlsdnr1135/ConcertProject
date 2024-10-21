package com.hhp.concertreservation.C_domain.member;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

//    @OneToMany(mappedBy = "user")
//    private Set<Token> tokens;
//
//    @OneToMany(mappedBy = "user")
//    private Set<Point> points;
}
