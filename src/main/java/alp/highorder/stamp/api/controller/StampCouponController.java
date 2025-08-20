package alp.highorder.stamp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import alp.highorder.stamp.api.dto.CouponDto;
import alp.highorder.stamp.api.dto.StampDto;
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
        stampCouponService.addStamp(customerId, storeId, 1);
        return ResponseEntity.ok("✅ 스탬프가 적립되었습니다. (10개 모이면 자동으로 쿠폰 발급)");
    }

    // ✅ 고객의 스탬프 조회
    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<StampDto.Response>> getStamps(@PathVariable Long customerId) {
        return ResponseEntity.ok(stampCouponService.getStamps(customerId));
    }

    // ✅ 스탬프 삭제
    @DeleteMapping("/{stampId}")
    public ResponseEntity<String> deleteStamp(@PathVariable Long stampId) {
        try {
            stampCouponService.deleteStamp(stampId);
            return ResponseEntity.ok("🗑️ 스탬프가 삭제되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("❌ 스탬프 삭제에 실패했습니다: " + e.getMessage());
        }
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

    // ✅ 쿠폰 상태 변경 (false -> true)
    @PatchMapping("/coupons/{couponId}/status")
    public ResponseEntity<String> changeCouponStatus(@PathVariable Long couponId) {
        try {
            stampCouponService.changeCouponStatus(couponId);
            return ResponseEntity.ok("✅ 쿠폰 상태가 변경되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("❌ 쿠폰 상태 변경 실패: " + e.getMessage());
        }
    }

    // ✅ 쿠폰 삭제
    @DeleteMapping("/coupons/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long couponId) {
        try {
            stampCouponService.deleteCoupon(couponId);
            return ResponseEntity.ok("🗑️ 쿠폰이 삭제되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("❌ 쿠폰 삭제에 실패했습니다: " + e.getMessage());
        }
    }
}
