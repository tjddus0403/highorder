package alp.highorder.review.domain.repository;

import alp.highorder.review.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMenuId(Long menuId);
    List<Review> findByCustomerId(Long customerId);
}
