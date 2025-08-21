package alp.highorder.order.domain.repository;

import alp.highorder.order.domain.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository {
    // 특정 주문의 주문 아이템들 조회
    List<OrderItem> findByOrderId(Long orderId);
    Optional<OrderItem> findById(Long id);
    OrderItem save(OrderItem orderItem);
}
