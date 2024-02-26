package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.UserLotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users/{userId}/lotteries")
public class UserLotteryController {

    private final UserLotteryService userLotteryService;

    @Autowired
    public UserLotteryController(UserLotteryService userLotteryService) {
        this.userLotteryService = userLotteryService;
    }

    @PostMapping("/{ticketId}")
    public ResponseEntity<Map<String, String>> buyLotteryTicket(
            @PathVariable Long userId,
            @PathVariable String ticketId
    ) {
        // Your authentication and authorization logic should be added here
        // For simplicity, let's assume some basic checks

        // Validate if the userId and ticketId are valid
        if (userId <= 0 || ticketId == null || ticketId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Call the service to process the purchase
        Long purchasedTicketId = userLotteryService.buyLotteryTicket(userId, ticketId);

        // Build the response
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("id", String.valueOf(purchasedTicketId));

        return ResponseEntity.ok(responseBody);
    }
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> deleteLotteryTicket(
            @PathVariable Long userId,
            @PathVariable String ticketId
    ) {
        // ตรวจสอบความถูกต้องของ userId และ ticketId
        if (userId <= 0 || ticketId == null || ticketId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // เรียกใช้งาน service สำหรับการลบ lottery ticket
        boolean deleted = userLotteryService.deleteLotteryTicket(userId, ticketId);

        // ตรวจสอบว่า lottery ticket ถูกลบหรือไม่
        if (deleted) {
            return ResponseEntity.ok("Ticket deleted: " + ticketId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found: " + ticketId);
        }
    }
}
