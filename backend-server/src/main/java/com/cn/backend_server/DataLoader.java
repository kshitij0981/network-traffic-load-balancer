package com.cn.backend_server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final SeatRepository repo;

    public DataLoader(SeatRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {

        // Reset seats whenever the server starts
        repo.deleteAll();

        String[] events = {
    "Championship Cricket Night",
    "National Football League",
    "International Tennis Masters",
    "Pro Basketball Night"
};

        List<Seat> seats = new ArrayList<>();

        for (String event : events) {

            for (int i = 1; i <= 500; i++) {

                seats.add(
                    new Seat(event, "A" + i)
                );
            }
        }

        repo.saveAll(seats);

        System.out.println("Created 2000 seats for 4 events");
    }
}