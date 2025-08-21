package alp.highorder.store.domain.repository;

import alp.highorder.store.domain.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    List<Store> findAll();
    Optional<Store> findById(Long id);
    Store save(Store store);
}
