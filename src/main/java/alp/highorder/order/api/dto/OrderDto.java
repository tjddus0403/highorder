package alp.highorder.order.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    public record OrderItemRequest(Long menuId, Integer quantity) {}

    public record CreateRequest(Long customerId, Long storeId, List<OrderItemRequest> items) {}

    public record Response(Long orderId, Long customerId, Long storeId, Integer totalPrice, List<OrderItemResponse> items, LocalDateTime orderedAt) {}

    public record OrderItemResponse(Long orderItemId, Long menuId, Integer quantity, Integer price) {}
}
