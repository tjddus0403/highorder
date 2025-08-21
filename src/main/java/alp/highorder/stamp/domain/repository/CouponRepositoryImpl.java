package alp.highorder.stamp.domain.repository;

import alp.highorder.stamp.domain.entity.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {
    
    private final CouponJpaRepository couponJpaRepository;
    
    @Override
    public List<Coupon> findByCustomerId(Long customerId) {
        return couponJpaRepository.findAll().stream()
                .filter(coupon -> coupon.getCustomer().getId().equals(customerId))
                .toList();
    }
    
    @Override
    public Optional<Coupon> findById(Long id) {
        return couponJpaRepository.findById(id);
    }
    
    @Override
    public Coupon save(Coupon coupon) {
        return couponJpaRepository.save(coupon);
    }
    
    @Override
    public void delete(Coupon coupon) {
        couponJpaRepository.delete(coupon);
    }
}
