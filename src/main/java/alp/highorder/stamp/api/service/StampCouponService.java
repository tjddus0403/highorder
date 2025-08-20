package alp.highorder.stamp.api.service;

import alp.highorder.customer.domain.entity.Customer;
import alp.highorder.customer.domain.repository.CustomerRepository;
import alp.highorder.store.domain.entity.Store;
import alp.highorder.store.domain.repository.StoreRepository;
import alp.highorder.stamp.api.dto.CouponDto;
import alp.highorder.stamp.domain.entity.Coupon;
import alp.highorder.stamp.domain.entity.Stamp;
import alp.highorder.stamp.domain.repository.CouponRepository;
import alp.highorder.stamp.domain.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StampCouponService {

    private final StampRepository stampRepository;
    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;

    // ✅ 스탬프 적립 + 10개 되면 쿠폰 발급
    public void addStamp(Long customerId, Long storeId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        Stamp stamp = stampRepository.findByCustomerIdAndStoreId(customerId, storeId)
            .orElse(Stamp.builder()
                    .customer(customer)
                    .store(store)
                    .count(0)
                    .updatedAt(LocalDateTime.now())
                    .build()
            );

        stamp.setCount(stamp.getCount() + 1);
        stamp.setUpdatedAt(LocalDateTime.now());
        stampRepository.save(stamp);

        // ✅ 10개 이상이면 쿠폰 발급 후 스탬프 초기화
        if (stamp.getCount() >= 10) {
            Coupon coupon = Coupon.builder()
                .customer(customer)
                .store(store)
                .used(false)
                .issuedAt(LocalDateTime.now()) // ✅ 수정
                .build();
            couponRepository.save(coupon);

            stamp.setCount(0);
            stampRepository.save(stamp);
        }
    }

    // ✅ 고객 쿠폰 조회 (DTO 변환)
    public List<CouponDto.Response> getCoupons(Long customerId) {
        return couponRepository.findByCustomerId(customerId).stream()
                .map(c -> new CouponDto.Response(
                        c.getId(),
                        c.getUsed(),
                        c.getIssuedAt(),
                        c.getStore().getId(),
                        c.getStore().getName()   // ✅ storeName 추가
                ))
                .toList();
    }

    // ✅ 쿠폰 사용
    public void useCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("쿠폰을 찾을 수 없습니다."));

        // ✅ 쿠폰 사용 시
        if (coupon.getUsed()) {   // isUsed() → getUsed()
            throw new RuntimeException("이미 사용된 쿠폰입니다.");
        }
        coupon.setUsed(true);
        couponRepository.save(coupon);
    }
}
