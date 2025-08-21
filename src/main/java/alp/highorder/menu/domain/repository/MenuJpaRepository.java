package alp.highorder.menu.domain.repository;

import alp.highorder.menu.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuJpaRepository extends JpaRepository<Menu, Long> {
    // Spring Data JPA의 기본 메서드들만 상속
}
