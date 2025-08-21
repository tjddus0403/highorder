package alp.highorder.review.api.service;

import alp.highorder.review.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto.Response createReview(ReviewDto.CreateRequest request);
    List<ReviewDto.Response> getReviewsByMenu(Long menuId);
    List<ReviewDto.Response> getReviewsByCustomer(Long customerId);
    ReviewDto.Response updateReview(Long reviewId, ReviewDto.UpdateRequest request);
    Long deleteReview(Long reviewId);
}
