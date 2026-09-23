package com.cn.backend_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class BookingController {

    private final SeatRepository repo;

    @Value("${server.port}")
    private int port;

    public BookingController(SeatRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/book")
    public Map<String, Object> book(
            @RequestParam String event,
            @RequestParam String seat) throws InterruptedException {

        // Simulate processing time
        Thread.sleep(ThreadLocalRandom.current().nextInt(50, 200));

        boolean ok = repo.book(event, seat) == 1;

        return Map.of(
                "success", ok,
                "event", event,
                "seat", seat,
                "server", port
        );
    }

    @GetMapping("/api/seats")
    public List<Seat> seats(@RequestParam String event) {

        List<Seat> list = new ArrayList<>(repo.findByEvent(event));

        list.sort(
                Comparator.comparingInt(
                        (Seat s) -> Integer.parseInt(
                                s.getSeatNumber().substring(1)
                        )
                )
        );

        return list;
    }
}