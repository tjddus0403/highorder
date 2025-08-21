package alp.highorder.order.api.service;

import alp.highorder.order.api.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto.Response createOrder(OrderDto.CreateRequest request);
    OrderDto.Response getOrder(Long orderId);
    OrderDto.OrderItemResponse getOrderItem(Long orderItemId);
    List<OrderDto.Response> getOrdersByCustomer(Long customerId);
    List<OrderDto.Response> getOrdersByStore(Long storeId);
}
