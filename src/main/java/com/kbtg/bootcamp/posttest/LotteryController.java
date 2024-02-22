package com.kbtg.bootcamp.posttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/lotteries")
public class LotteryController {

    private final LotteryService lotteryService;

    @Autowired
    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }
    @PostMapping
    public ResponseEntity<Map<String, String>> addLotteryTicket(@RequestBody Map<String, String> request) {
        // Your basic authentication logic should be added here
        // For simplicity, let's assume username=admin and password=password
        String username = request.get("username");
        String password = request.get("password");

        if (!"admin".equals(username) || !"password".equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String ticket = request.get("ticket");
        String addedTicket = lotteryService.addLotteryTicket(ticket);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("ticket", addedTicket);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }


    // Add more methods as needed
    @GetMapping
    public List<String> getAllLotteryTickets() {
        return lotteryService.getAllLotteryTickets();
    }
}
