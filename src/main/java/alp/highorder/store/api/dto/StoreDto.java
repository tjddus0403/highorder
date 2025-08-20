package alp.highorder.store.api.dto;

public class StoreDto {

    public record CreateRequest(
            String name,
            String description,
            String address,
            Double latitude,
            Double longitude,
            String phone,
            String logoUri // ✅ 로고 이미지 경로
    ) {}

    public record Response(
            Long id,
            String name,
            String description,
            String address,
            Double latitude,
            Double longitude,
            String phone,
            String logoUri // ✅ 응답에도 포함
    ) {}
}
