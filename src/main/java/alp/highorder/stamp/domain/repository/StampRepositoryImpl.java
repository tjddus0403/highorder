package alp.highorder.stamp.domain.repository;

import alp.highorder.stamp.domain.entity.Stamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StampRepositoryImpl implements StampRepository {
    
    private final StampJpaRepository stampJpaRepository;
    
    @Override
    public Optional<Stamp> findByCustomerIdAndStoreId(Long customerId, Long storeId) {
        return stampJpaRepository.findAll().stream()
                .filter(stamp -> stamp.getCustomer().getId().equals(customerId) &&
                                 stamp.getStore().getId().equals(storeId))
                .findFirst();
    }
    
    @Override
    public List<Stamp> findByCustomerId(Long customerId) {
        return stampJpaRepository.findAll().stream()
                .filter(stamp -> stamp.getCustomer().getId().equals(customerId))
                .toList();
    }
    
    @Override
    public Optional<Stamp> findById(Long id) {
        return stampJpaRepository.findById(id);
    }
    
    @Override
    public Stamp save(Stamp stamp) {
        return stampJpaRepository.save(stamp);
    }
    
    @Override
    public void delete(Stamp stamp) {
        stampJpaRepository.delete(stamp);
    }
}
