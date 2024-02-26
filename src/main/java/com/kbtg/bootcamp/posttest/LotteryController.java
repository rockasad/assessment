package com.kbtg.bootcamp.posttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author osas
 */
@RestController
@RequestMapping("/admin/lotteries")
public class LotteryController {

    private final LotteryService lotteryService;

    @Autowired
    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addLotteryTicket(@RequestBody Map<String, Object> request) {
        String username = request.get("username").toString();
        String password = request.get("password").toString();

        if (!"admin".equals(username) || !"password".equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String ticket = request.get("ticket").toString();
        int price = (int) request.get("price");
        int amount = (int) request.get("amount");

        String addedTicket = lotteryService.addLotteryTicket(ticket, price, amount);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("ticket", addedTicket);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }


    // Add more methods as needed
    @GetMapping
    public ResponseEntity<Map<String, String>> getAllLotteryTickets() {
        // Implement this method if needed
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
