package com.vtw.dna.movie;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String director;

    private Date date;

    public Movie update(Movie newOne) {
        this.subject = newOne.subject;
        this.director = newOne.director;
        this.date = newOne.date;
        return this;
    }

}
