package alp.highorder.order.domain.repository;

import alp.highorder.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 고객별 주문 조회
    List<Order> findByCustomerId(Long customerId);

    // 가게별 주문 조회
    List<Order> findByStoreId(Long storeId);
}
