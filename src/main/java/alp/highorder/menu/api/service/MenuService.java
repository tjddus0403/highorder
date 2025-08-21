package alp.highorder.menu.api.service;

import alp.highorder.menu.api.dto.MenuDto;

import java.util.List;

public interface MenuService {
    MenuDto.Response createMenu(MenuDto.CreateRequest request);
    List<MenuDto.Response> getMenusByStore(Long storeId);
    MenuDto.Response getMenu(Long id);
}
