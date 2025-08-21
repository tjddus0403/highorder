package alp.highorder.stamp.api.service;

import alp.highorder.customer.domain.entity.Customer;
import alp.highorder.customer.domain.repository.CustomerRepository;
import alp.highorder.store.domain.entity.Store;
import alp.highorder.store.domain.repository.StoreRepository;
import alp.highorder.stamp.api.dto.CouponDto;
import alp.highorder.stamp.api.dto.StampDto;
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
public class StampCouponServiceImpl implements StampCouponService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final StampRepository stampRepository;
    private final CouponRepository couponRepository;

    @Override
    public void addStamp(Long customerId, Long storeId, int count) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        // 기존 스탬프 조회 or 신규 생성
        Stamp stamp = stampRepository.findByCustomerIdAndStoreId(customerId, storeId)
                .orElse(Stamp.builder()
                        .customer(customer)
                        .store(store)
                        .count(0)
                        .updatedAt(LocalDateTime.now())
                        .build()
                );

        // count 만큼 적립
        stamp.setCount(stamp.getCount() + count);
        stamp.setUpdatedAt(LocalDateTime.now());

        // ✅ 10개 이상이면 쿠폰 발급 후 개수 차감
        while (stamp.getCount() >= 10) {
            Coupon coupon = Coupon.builder()
                    .customer(customer)
                    .store(store)
                    .used(false)
                    .issuedAt(LocalDateTime.now())
                    .build();
            couponRepository.save(coupon);

            stamp.setCount(stamp.getCount() - 10); // 10개 차감
        }

        stampRepository.save(stamp);
    }

    @Override
    public List<StampDto.Response> getStamps(Long customerId) {
        return stampRepository.findByCustomerId(customerId).stream()
                .map(s -> new StampDto.Response(
                        s.getId(),
                        s.getCustomer().getId(),
                        s.getStore().getId(),
                        s.getCount(),
                        s.getUpdatedAt()
                ))
                .toList();
    }

    @Override
    public void deleteStamp(Long stampId) {
        var stamp = stampRepository.findById(stampId)
                .orElseThrow(() -> new RuntimeException("Stamp not found"));

        stampRepository.delete(stamp);
    }

    @Override
    public List<CouponDto.Response> getCoupons(Long customerId) {
        return couponRepository.findByCustomerId(customerId).stream()
                .map(c -> new CouponDto.Response(
                        c.getId(),
                        c.getCustomer().getId(),
                        c.getStore().getId(),
                        c.isUsed(),
                        c.getIssuedAt()
                ))
                .toList();
    }

    @Override
    public void changeCouponStatus(Long couponId) {
        var coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        // used = false → true 로 변경
        if (!coupon.isUsed()) {
            coupon.setUsed(true);
            couponRepository.save(coupon);
        } else {
            throw new RuntimeException("이미 사용된 쿠폰입니다.");
        }
    }

    @Override
    public void useCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("쿠폰을 찾을 수 없습니다."));
        if (coupon.isUsed()) {
            throw new RuntimeException("이미 사용된 쿠폰입니다.");
        }
        coupon.setUsed(true);
        couponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(Long couponId) {
        var coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        couponRepository.delete(coupon);
    }
}
