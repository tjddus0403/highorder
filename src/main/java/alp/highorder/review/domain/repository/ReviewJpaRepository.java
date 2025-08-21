package alp.highorder.review.domain.repository;

import alp.highorder.review.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
    // Spring Data JPA의 기본 메서드들만 상속
}
