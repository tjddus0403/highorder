package alp.highorder.menu.domain.repository;

import alp.highorder.menu.domain.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {
    
    private final MenuJpaRepository menuJpaRepository;
    
    @Override
    public List<Menu> findByStoreId(Long storeId) {
        return menuJpaRepository.findAll().stream()
                .filter(menu -> menu.getStore().getId().equals(storeId))
                .toList();
    }
    
    @Override
    public Optional<Menu> findById(Long id) {
        return menuJpaRepository.findById(id);
    }
    
    @Override
    public Menu save(Menu menu) {
        return menuJpaRepository.save(menu);
    }
}
