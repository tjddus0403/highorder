package alp.highorder.stamp.api.dto;

import java.time.LocalDateTime;

public class CouponDto {

    public record Response(
        Long id,
        Long customerId,
        Long storeId,
        boolean used,         // ✅ boolean
        LocalDateTime issuedAt
    ) {}
}
