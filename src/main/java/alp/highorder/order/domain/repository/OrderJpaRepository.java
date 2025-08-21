package alp.highorder.order.domain.repository;

import alp.highorder.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    // Spring Data JPA의 기본 메서드들만 상속
}
