package com.example.demo.repository;

import com.example.demo.entity.Seats;
import com.example.demo.entity.Theaters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seats, Integer> {

    List<Seats> findByTheaterId(Theaters theaterId);
}
