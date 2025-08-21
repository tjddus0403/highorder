// CouponRepository.java
package alp.highorder.stamp.domain.repository;

import alp.highorder.stamp.domain.entity.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponRepository {
    List<Coupon> findByCustomerId(Long customerId);
    Optional<Coupon> findById(Long id);
    Coupon save(Coupon coupon);
    void delete(Coupon coupon);
}
