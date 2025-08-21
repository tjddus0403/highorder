package alp.highorder.store.domain.repository;

import alp.highorder.store.domain.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {
    
    private final StoreJpaRepository storeJpaRepository;
    
    @Override
    public List<Store> findAll() {
        return storeJpaRepository.findAll();
    }
    
    @Override
    public Optional<Store> findById(Long id) {
        return storeJpaRepository.findById(id);
    }
    
    @Override
    public Store save(Store store) {
        return storeJpaRepository.save(store);
    }
}
