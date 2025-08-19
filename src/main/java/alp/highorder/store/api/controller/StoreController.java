package alp.highorder.store.api.controller;

import alp.highorder.menu.api.dto.MenuDto;
import alp.highorder.menu.api.service.MenuService;
import alp.highorder.store.api.dto.StoreDto;
import alp.highorder.store.api.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<StoreDto.Response> createStore(@RequestBody StoreDto.CreateRequest request) {
        return ResponseEntity.ok(storeService.createStore(request));
    }

    // 전체 가게 목록 조회
    @GetMapping
    public ResponseEntity<List<StoreDto.Response>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    // 특정 가게 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<StoreDto.Response> getStore(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStore(id));
    }

    // 특정 가게의 메뉴 조회
    @GetMapping("/{storeId}/menus")
    public ResponseEntity<List<MenuDto.Response>> getMenusByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(menuService.getMenusByStore(storeId));
    }
}
