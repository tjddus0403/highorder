package alp.highorder.review.api.dto;

import java.time.LocalDateTime;

public class ReviewDto {

    public record CreateRequest(Long customerId, Long orderId, Long menuId, int rating, String comment) {}

    public record Response(Long id, Long customerId, Long orderId, Long menuId,
                           int rating, String comment, LocalDateTime createdAt) {}
}
