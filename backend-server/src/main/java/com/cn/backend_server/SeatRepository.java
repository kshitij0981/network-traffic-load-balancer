package com.cn.backend_server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {

    // Book a specific seat for a specific event
    @Modifying
    @Transactional
    @Query("""
           UPDATE Seat s
           SET s.booked = true
           WHERE s.event = :event
           AND s.seatNumber = :seatNumber
           AND s.booked = false
           """)
    int book(
            @Param("event") String event,
            @Param("seatNumber") String seatNumber
    );

    // Get seats for one specific event
    List<Seat> findByEvent(String event);
}
