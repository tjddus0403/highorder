package alp.highorder.store.domain.repository;

import alp.highorder.store.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<Store, Long> {
    // Spring Data JPA의 기본 메서드들만 상속
}
