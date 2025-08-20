package alp.highorder.review.domain.repository;

import alp.highorder.review.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 고객이 작성한 모든 리뷰
    List<Review> findByCustomerId(Long customerId);

    // 특정 메뉴에 달린 리뷰 (Review → OrderItem → Menu)
    List<Review> findByOrderItemMenuId(Long menuId);

    // 특정 주문항목 리뷰 (예: 한 주문 메뉴당 하나만 작성하게 하고 싶을 때)
    List<Review> findByOrderItemId(Long orderItemId);

    // 고객이 특정 주문항목에 이미 리뷰 작성했는지 확인할 때
    boolean existsByOrderItemIdAndCustomerId(Long orderItemId, Long customerId);
}
