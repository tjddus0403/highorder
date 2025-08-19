package alp.highorder.menu.api.dto;

public class MenuDto {
    public record CreateRequest(Long storeId, String name, String description, Integer price) {}
    public record Response(Long id, String name, String description, Integer price, Long storeId, Double avgRating, Integer reviewCount) {}
}
