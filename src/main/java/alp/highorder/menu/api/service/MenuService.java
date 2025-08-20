package alp.highorder.menu.api.service;

import alp.highorder.menu.api.dto.MenuDto;
import alp.highorder.menu.domain.entity.Menu;
import alp.highorder.menu.domain.repository.MenuRepository;
import alp.highorder.store.domain.entity.Store;
import alp.highorder.store.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public MenuDto.Response createMenu(MenuDto.CreateRequest request) {
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Menu menu = Menu.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .store(store)
                .avgRating(0.0)
                .reviewCount(0)
                .imageUri(request.imageUri())   // ✅ 이미지 추가
                .build();

        Menu saved = menuRepository.save(menu);
        return new MenuDto.Response(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStore().getId(),
                saved.getAvgRating(),
                saved.getReviewCount(),
                saved.getImageUri()
        );
    }

    public List<MenuDto.Response> getMenusByStore(Long storeId) {
        return menuRepository.findByStoreId(storeId).stream()
                .map(m -> new MenuDto.Response(
                        m.getId(),
                        m.getName(),
                        m.getDescription(),
                        m.getPrice(),
                        m.getStore().getId(),
                        m.getAvgRating(),
                        m.getReviewCount(),
                        m.getImageUri()
                ))
                .toList();
    }

    public MenuDto.Response getMenu(Long id) {
        var menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        return new MenuDto.Response(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getStore().getId(),
                menu.getAvgRating(),
                menu.getReviewCount(),
                menu.getImageUri()
        );
    }
}
