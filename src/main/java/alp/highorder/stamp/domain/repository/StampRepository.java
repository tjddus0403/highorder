// StampRepository.java
package alp.highorder.stamp.domain.repository;

import alp.highorder.stamp.domain.entity.Stamp;

import java.util.Optional;
import java.util.List;

public interface StampRepository {
    Optional<Stamp> findByCustomerIdAndStoreId(Long customerId, Long storeId);
    List<Stamp> findByCustomerId(Long customerId);
    Optional<Stamp> findById(Long id);
    Stamp save(Stamp stamp);
    void delete(Stamp stamp);
}
