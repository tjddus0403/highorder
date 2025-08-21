package alp.highorder.store.api.service;

import alp.highorder.store.api.dto.StoreDto;

import java.util.List;

public interface StoreService {
    StoreDto.Response createStore(StoreDto.CreateRequest request);
    List<StoreDto.Response> getAllStores();
    StoreDto.Response getStore(Long id);
}
