package com.example.demo.service;

import com.example.demo.entity.Bookings;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Bookings> findAll(){
        return bookingRepository.findAll();
    }

    public Bookings findBookingById(int id){
        Optional<Bookings> result = bookingRepository.findById(id);
        Bookings booking;

        if(result.isPresent()){
            booking = result.get();
        }else{
            throw new RuntimeException("Booking id not found : "+ id);
        }
        return booking;
    }

    public void bookTicket(int userId, int showtimeId, int noOfSeats,String selectSeatsIds ){
        bookingRepository.book_a_ticket(userId,showtimeId,noOfSeats,selectSeatsIds);
    }
}
