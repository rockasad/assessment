package com.kbtg.bootcamp.posttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LotteryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String addLotteryTicket(String ticket, int price, int amount) {
        // ทำการแทรกข้อมูลลอตเตอรี่ลงในตาราง lottery
        jdbcTemplate.update("INSERT INTO lottery (lottery_number, price, amount) VALUES (?, ?, ?)", ticket, price, amount);

        return ticket;
    }

    public List<String> getAllLotteryTickets() {
        // ดึงข้อมูลทั้งหมดจากตาราง lottery
        return jdbcTemplate.queryForList("SELECT lottery_number FROM lottery", String.class);
    }

    // Add more methods as needed
}