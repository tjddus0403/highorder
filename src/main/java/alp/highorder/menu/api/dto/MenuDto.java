package alp.highorder.menu.api.dto;

public class MenuDto {

    public record CreateRequest(
            Long storeId,
            String name,
            String description,
            Integer price,
            String imageUri // ✅ 메뉴 이미지 경로
    ) {}

    // public record Response(
    //         Long id,
    //         String name,
    //         String description,
    //         Integer price,
    //         Long storeId,
    //         String imageUri // ✅ 응답에도 포함
    // ) {}

    public record Response(
        Long id,
        String name,
        String description,
        int price,
        Long storeId,
        Double avgRating,
        int reviewCount,
        String imageUri // ✅ 추가
    ) {}
}
