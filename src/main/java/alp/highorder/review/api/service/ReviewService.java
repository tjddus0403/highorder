package alp.highorder.review.api.service;

import alp.highorder.customer.domain.repository.CustomerRepository;
import alp.highorder.menu.domain.repository.MenuRepository;
import alp.highorder.order.domain.repository.OrderRepository;
import alp.highorder.review.api.dto.ReviewDto;
import alp.highorder.review.domain.entity.Review;
import alp.highorder.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    // 리뷰 작성
    public ReviewDto.Response createReview(ReviewDto.CreateRequest request) {
        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        var order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        var menu = menuRepository.findById(request.menuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        Review review = Review.builder()
                .customer(customer)
                .order(order)
                .menu(menu)
                .rating(request.rating())
                .comment(request.comment())
                .createdAt(LocalDateTime.now())
                .build();

        Review saved = reviewRepository.save(review);

        // ✅ 리뷰 저장 직후 메뉴의 avgRating, reviewCount 갱신
        List<Review> reviews = reviewRepository.findByMenuId(menu.getId());
        double avg = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
        menu.setAvgRating(Math.round(avg * 100.0) / 100.0); // 소수점 2자리
        menu.setReviewCount(reviews.size());
        menuRepository.save(menu);

        return new ReviewDto.Response(
                saved.getId(), saved.getCustomer().getId(), saved.getOrder().getId(),
                saved.getMenu().getId(), saved.getRating(), saved.getComment(), saved.getCreatedAt()
        );
    }


    // 특정 메뉴 리뷰 조회
    public List<ReviewDto.Response> getReviewsByMenu(Long menuId) {
        return reviewRepository.findByMenuId(menuId).stream()
                .map(r -> new ReviewDto.Response(r.getId(), r.getCustomer().getId(),
                        r.getOrder().getId(), r.getMenu().getId(),
                        r.getRating(), r.getComment(), r.getCreatedAt()))
                .toList();
    }

    // 고객별 리뷰 조회
    public List<ReviewDto.Response> getReviewsByCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId).stream()
                .map(r -> new ReviewDto.Response(r.getId(), r.getCustomer().getId(),
                        r.getOrder().getId(), r.getMenu().getId(),
                        r.getRating(), r.getComment(), r.getCreatedAt()))
                .toList();
    }
}
