package alp.highorder.menu.domain.repository;

import alp.highorder.menu.domain.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    List<Menu> findByStoreId(Long storeId);
    Optional<Menu> findById(Long id);
    Menu save(Menu menu);
}
