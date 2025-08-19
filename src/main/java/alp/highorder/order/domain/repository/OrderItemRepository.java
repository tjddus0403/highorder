package alp.highorder.order.domain.repository;

import alp.highorder.order.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // 특정 주문의 주문 아이템들 조회
    List<OrderItem> findByOrderId(Long orderId);
}
