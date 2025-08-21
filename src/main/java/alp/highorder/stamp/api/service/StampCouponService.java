package alp.highorder.stamp.api.service;

import alp.highorder.stamp.api.dto.CouponDto;
import alp.highorder.stamp.api.dto.StampDto;

import java.util.List;

public interface StampCouponService {
    void addStamp(Long customerId, Long storeId, int count);
    List<StampDto.Response> getStamps(Long customerId);
    void deleteStamp(Long stampId);
    List<CouponDto.Response> getCoupons(Long customerId);
    void changeCouponStatus(Long couponId);
    void useCoupon(Long couponId);
    void deleteCoupon(Long couponId);
}
