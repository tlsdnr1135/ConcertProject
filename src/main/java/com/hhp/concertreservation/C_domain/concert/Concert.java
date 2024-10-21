package com.hhp.concertreservation.C_domain.concert;


import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "CONCERT")
public class Concert {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;

    @Column(name = "TITLE")
    private String title;

}
