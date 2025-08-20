// CouponRepository.java
package alp.highorder.stamp.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import alp.highorder.stamp.domain.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByCustomerId(Long customerId);
}
