package alp.highorder.review.api.dto;

import java.time.LocalDateTime;

public class ReviewDto {

    // 리뷰 생성 요청
    public record CreateRequest(
            Long customerId,
            Long orderItemId,
            int rating,
            String comment
    ) {}

    // 리뷰 수정 요청
    public record UpdateRequest(
            int rating,
            String comment
    ) {}

    // 리뷰 응답
    public record Response(
            Long id,
            Long customerId,
            Long orderItemId,
            int rating,
            String comment,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
}
