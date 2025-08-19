package alp.highorder.store.domain.repository;

import alp.highorder.store.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
