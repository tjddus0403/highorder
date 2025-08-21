package alp.highorder.review.api.service;

import alp.highorder.customer.domain.repository.CustomerRepository;
import alp.highorder.order.domain.repository.OrderItemRepository;
import alp.highorder.review.api.dto.ReviewDto;
import alp.highorder.review.domain.entity.Review;
import alp.highorder.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public ReviewDto.Response createReview(ReviewDto.CreateRequest request) {
        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        var orderItem = orderItemRepository.findById(request.orderItemId())
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        Review review = Review.builder()
                .customer(customer)
                .orderItem(orderItem)
                .rating(request.rating())
                .comment(request.comment())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Review saved = reviewRepository.save(review);

        return new ReviewDto.Response(
                saved.getId(),
                saved.getCustomer().getId(),
                saved.getOrderItem().getId(),
                saved.getRating(),
                saved.getComment(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }

    @Override
    public List<ReviewDto.Response> getReviewsByMenu(Long menuId) {
        return reviewRepository.findByOrderItemMenuId(menuId).stream()
                .map(r -> new ReviewDto.Response(
                        r.getId(),
                        r.getCustomer().getId(),
                        r.getOrderItem().getId(),
                        r.getRating(),
                        r.getComment(),
                        r.getCreatedAt(),
                        r.getUpdatedAt()
                ))
                .toList();
    }

    @Override
    public List<ReviewDto.Response> getReviewsByCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId).stream()
                .map(r -> new ReviewDto.Response(
                        r.getId(),
                        r.getCustomer().getId(),
                        r.getOrderItem().getId(),
                        r.getRating(),
                        r.getComment(),
                        r.getCreatedAt(),
                        r.getUpdatedAt()
                ))
                .toList();
    }

    @Override
    @Transactional
    public ReviewDto.Response updateReview(Long reviewId, ReviewDto.UpdateRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setRating(request.rating());
        review.setComment(request.comment());
        review.setUpdatedAt(LocalDateTime.now());

        Review updated = reviewRepository.save(review);

        return new ReviewDto.Response(
                updated.getId(),
                updated.getCustomer().getId(),
                updated.getOrderItem().getId(),
                updated.getRating(),
                updated.getComment(),
                updated.getCreatedAt(),
                updated.getUpdatedAt()
        );
    }

    @Override
    @Transactional
    public Long deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewRepository.delete(review);
        return reviewId;
    }
}
