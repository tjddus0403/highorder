package alp.highorder.order.domain.repository;

import alp.highorder.order.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
    // Spring Data JPA의 기본 메서드들만 상속
}
