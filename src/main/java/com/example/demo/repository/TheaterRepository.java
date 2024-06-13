package com.example.demo.repository;

import com.example.demo.entity.Theaters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theaters, Integer> {
}
