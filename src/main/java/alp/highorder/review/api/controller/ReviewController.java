package alp.highorder.review.api.controller;

import alp.highorder.review.api.dto.ReviewDto;
import alp.highorder.review.api.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping
    public ResponseEntity<ReviewDto.Response> createReview(@RequestBody ReviewDto.CreateRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    // 특정 메뉴 리뷰 조회
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<ReviewDto.Response>> getReviewsByMenu(@PathVariable Long menuId) {
        return ResponseEntity.ok(reviewService.getReviewsByMenu(menuId));
    }

    // 고객별 리뷰 조회
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReviewDto.Response>> getReviewsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customerId));
    }

    // ✅ 리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto.Response> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewDto.UpdateRequest request
    ) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, request));
    }

    // ✅ 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
