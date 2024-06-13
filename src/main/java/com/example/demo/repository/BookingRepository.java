package com.example.demo.repository;

import com.example.demo.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer> {

    @Procedure(name="book_a_ticket")
    void book_a_ticket(
            @Param("user_id") int userId,
            @Param("showtime_id") int showtimeId,
            @Param("no_of_seats") int noOfSeats,
            @Param("selected_seat_ids") String selectedSeatIds

    );
}
