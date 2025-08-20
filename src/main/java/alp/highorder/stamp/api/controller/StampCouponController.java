package alp.highorder.stamp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import alp.highorder.stamp.api.dto.CouponDto;
import alp.highorder.stamp.api.service.StampCouponService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stamps")
@RequiredArgsConstructor
public class StampCouponController {

    private final StampCouponService stampCouponService;

    // ✅ 주문 완료 후 스탬프 적립 (+ 자동 쿠폰 발급)
    @PostMapping("/add")
    public ResponseEntity<String> addStamp(@RequestParam Long customerId, @RequestParam Long storeId) {
        stampCouponService.addStamp(customerId, storeId);
        return ResponseEntity.ok("✅ 스탬프가 적립되었습니다. (10개 모이면 자동으로 쿠폰 발급)");
    }

    // ✅ 고객의 쿠폰 조회
    @GetMapping("/coupons/{customerId}")
    public ResponseEntity<List<CouponDto.Response>> getCoupons(@PathVariable Long customerId) {
        return ResponseEntity.ok(stampCouponService.getCoupons(customerId));
    }

    // ✅ 쿠폰 사용
    @PostMapping("/use/{couponId}")
    public ResponseEntity<String> useCoupon(@PathVariable Long couponId) {
        stampCouponService.useCoupon(couponId);
        return ResponseEntity.ok("✅ 쿠폰이 사용되었습니다.");
    }
}
