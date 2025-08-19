package alp.highorder.store.api.dto;

public class StoreDto {
    public record CreateRequest(String name, String description, String address,
                                Double latitude, Double longitude, String phone) {}
    public record Response(Long id, String name, String description, String address,
                           Double latitude, Double longitude, String phone) {}
}
