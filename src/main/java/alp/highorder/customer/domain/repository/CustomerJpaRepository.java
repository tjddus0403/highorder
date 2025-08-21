package alp.highorder.customer.domain.repository;

import alp.highorder.customer.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
    // Spring Data JPA의 기본 메서드들만 상속
}
