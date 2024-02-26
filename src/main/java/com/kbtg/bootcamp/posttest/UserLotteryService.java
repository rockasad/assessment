package com.kbtg.bootcamp.posttest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class UserLotteryService {

    private final JdbcTemplate jdbcTemplate;

    public UserLotteryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long buyLotteryTicket(Long userId, String ticketId) {
        jdbcTemplate.update("INSERT INTO user_ticket (user_id, lottery_id) VALUES (?, (SELECT lottery_id FROM lottery WHERE lottery_number = ?))", userId, ticketId);

        // ค้นหา id ที่ถูกสร้างขึ้นล่าสุด
        Long userTicketId = jdbcTemplate.queryForObject("SELECT lastval()", Long.class);

        // ส่งคืน userTicketId ที่ถูกสร้างขึ้นล่าสุด
        return userTicketId;
    }

    public boolean deleteLotteryTicket(Long userId, String ticketId) {
        if (userId <= 0 || ticketId == null || ticketId.isEmpty()) {
            return false;
        }

        // ดำเนินการลบ lottery ticket จากฐานข้อมูล (ตัดสินใจลบหรือไม่ลบตามที่คุณต้องการ)
        int rowsAffected = jdbcTemplate.update("DELETE FROM user_ticket WHERE user_id = ? AND lottery_id = (SELECT lottery_id FROM lottery WHERE lottery_number = ?)", userId, ticketId);
        // สำเร็จเมื่อลบ lottery ticket ได้
        return rowsAffected > 0;
    }

}
