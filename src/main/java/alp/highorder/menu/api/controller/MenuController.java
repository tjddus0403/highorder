package alp.highorder.menu.api.controller;

import alp.highorder.menu.api.dto.MenuDto;
import alp.highorder.menu.api.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    
    // ✅ 메뉴 ID로 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MenuDto.Response> getMenu(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenu(id));
    }
}
