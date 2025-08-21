package alp.highorder.order.domain.repository;

import alp.highorder.order.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    // 고객별 주문 조회
    List<Order> findByCustomerId(Long customerId);
    // 가게별 주문 조회
    List<Order> findByStoreId(Long storeId);
    Optional<Order> findById(Long id);
    Order save(Order order);
}
