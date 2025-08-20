package alp.highorder.store.api.service;

import alp.highorder.store.api.dto.StoreDto;
import alp.highorder.store.domain.entity.Store;
import alp.highorder.store.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreDto.Response createStore(StoreDto.CreateRequest request) {
        Store store = Store.builder()
                .name(request.name())
                .description(request.description())
                .address(request.address())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .phone(request.phone())
                .logoUri(request.logoUri())   // ✅ 로고 이미지 추가
                .build();

        Store saved = storeRepository.save(store);
        return new StoreDto.Response(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getAddress(),
                saved.getLatitude(),
                saved.getLongitude(),
                saved.getPhone(),
                saved.getLogoUri()
        );
    }

    public List<StoreDto.Response> getAllStores() {
        return storeRepository.findAll().stream()
                .map(s -> new StoreDto.Response(
                        s.getId(),
                        s.getName(),
                        s.getDescription(),
                        s.getAddress(),
                        s.getLatitude(),
                        s.getLongitude(),
                        s.getPhone(),
                        s.getLogoUri()
                ))
                .toList();
    }

    public StoreDto.Response getStore(Long id) {
        var s = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        return new StoreDto.Response(
                s.getId(),
                s.getName(),
                s.getDescription(),
                s.getAddress(),
                s.getLatitude(),
                s.getLongitude(),
                s.getPhone(),
                s.getLogoUri()
        );
    }
}
