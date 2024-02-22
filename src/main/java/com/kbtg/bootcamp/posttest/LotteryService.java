package com.kbtg.bootcamp.posttest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LotteryService {

    private final List<String> lotteryTickets = new ArrayList<>();

    public String addLotteryTicket(String ticket) {
        lotteryTickets.add(ticket);
        return ticket;
    }

    public List<String> getAllLotteryTickets() {
        return lotteryTickets;
    }
}
