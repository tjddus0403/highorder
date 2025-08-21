package alp.highorder.stamp.domain.repository;

import alp.highorder.stamp.domain.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampJpaRepository extends JpaRepository<Stamp, Long> {
    // Spring Data JPA의 기본 메서드들만 상속
}
