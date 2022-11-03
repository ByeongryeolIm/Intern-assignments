package com.vtw.dna.movie;

import com.vtw.dna.movie.Movie;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cinema;

    private String seat;

    private Date viewDate;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
