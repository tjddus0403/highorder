package alp.highorder.stamp.api.dto;

import java.time.LocalDateTime;

public class CouponDto {

    public record Response(
            Long id,
            boolean used,
            LocalDateTime createdAt,
            Long storeId,
            String storeName
    ) {}
}
