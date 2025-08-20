// StampDto.java
package alp.highorder.stamp.api.dto;

import java.time.LocalDateTime;

public record StampDto(
        Long id,
        Long customerId,
        Long storeId,
        int count,
        LocalDateTime updatedAt
) {}
