package alp.highorder.customer.api.dto;

public class CustomerDto {
    public record LoginRequest(String email, String password) {}
    public record CreateRequest(String name, String email, String password, String nickname) {}
    public record Response(Long id, String name, String email, String nickname) {}
}
