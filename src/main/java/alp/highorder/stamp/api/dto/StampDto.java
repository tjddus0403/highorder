package alp.highorder.stamp.api.dto;

import java.time.LocalDateTime;

public class StampDto {

    // 📌 스탬프 조회용 응답 DTO
    public record Response(
            Long id,
            Long customerId,
            Long storeId,
            int count,
            LocalDateTime updatedAt
    ) {}
}