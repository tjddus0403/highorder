// StampRepository.java
package alp.highorder.stamp.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import alp.highorder.stamp.domain.entity.Stamp;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    Optional<Stamp> findByCustomerIdAndStoreId(Long customerId, Long storeId);
}
