package com.vtw.dna.movie.repository;

import com.vtw.dna.movie.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findAllByCinemaContains(Pageable pageable, String searchName);

}
