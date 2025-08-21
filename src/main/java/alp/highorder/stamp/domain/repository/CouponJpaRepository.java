package alp.highorder.stamp.domain.repository;

import alp.highorder.stamp.domain.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {
    // Spring Data JPA의 기본 메서드들만 상속
}
