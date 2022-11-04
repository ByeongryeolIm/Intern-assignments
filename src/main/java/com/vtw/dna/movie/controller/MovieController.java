package com.vtw.dna.movie.controller;

import com.vtw.dna.movie.Movie;
import com.vtw.dna.movie.Reservation;
import com.vtw.dna.movie.repository.MovieRepository;
import com.vtw.dna.movie.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository repository;
    private final ReservationRepository reservationRepository;

    @GetMapping
    public Page<Movie> list(@RequestParam("page") int page,
                            @RequestParam("size") int size,
                            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                            @RequestParam(value = "filter", defaultValue = "") String filter){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Movie> movies = repository.findAllBySubjectContains(pageable, filter);
        return  movies;
    }

    @PostMapping
    public Movie create(@RequestBody Movie newOne) {
        repository.save(newOne);
        return newOne;
    }

    @GetMapping("/{id}")
    public Movie find(@PathVariable Long id) {
        Movie movie = repository.findById(id).orElseThrow();
        return movie;
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie newOne) {
        Movie oldOne = repository.findById(id).orElseThrow();
        oldOne.update(newOne);
        repository.save(oldOne);
        return oldOne;
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable Long id) {
        System.out.println("id값:"+id);
        Movie oldOne = repository.findById(id).orElseThrow();
        repository.delete(oldOne);
        return oldOne;
    }

    @PostMapping("/reserve")
    public Reservation reserve(@RequestBody Reservation reserve) {
        reservationRepository.save(reserve);
        return reserve;
    }
    @GetMapping("/reserveList")
    public Page<Reservation> reserveList(@RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                                         @RequestParam(value = "filter", defaultValue = "") String filter) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Reservation> reservations= reservationRepository.findAllByCinemaContains(pageable,filter);
        return reservations;
    }
    @DeleteMapping("/reserveCancel/{id}")
    public Reservation reserveCancel(@PathVariable Long id) {
        System.out.println("id값:"+id);
        Reservation oldOne = reservationRepository.findById(id).orElseThrow();
        reservationRepository.delete(oldOne);
        return oldOne;
    }

}
