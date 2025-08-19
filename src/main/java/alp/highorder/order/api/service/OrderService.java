package alp.highorder.order.api.service;

import alp.highorder.customer.domain.repository.CustomerRepository;
import alp.highorder.store.domain.repository.StoreRepository;
import alp.highorder.menu.domain.repository.MenuRepository;
import alp.highorder.order.api.dto.OrderDto;
import alp.highorder.order.domain.entity.Order;
import alp.highorder.order.domain.entity.OrderItem;
import alp.highorder.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    // ✅ 주문 생성
    public OrderDto.Response createOrder(OrderDto.CreateRequest request) {
        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        var store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Order order = Order.builder()
                .customer(customer)
                .store(store)
                .orderedAt(LocalDateTime.now())
                .build();

        int totalPrice = 0;
        for (OrderDto.OrderItemRequest itemReq : request.items()) {
            var menu = menuRepository.findById(itemReq.menuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));

            int price = menu.getPrice() * itemReq.quantity();
            totalPrice += price;

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menu(menu)
                    .quantity(itemReq.quantity())
                    .price(price)
                    .build();

            order.getItems().add(orderItem);
        }
        order.setTotalPrice(totalPrice);

        Order saved = orderRepository.save(order);
        return toResponse(saved);
    }

    // ✅ 주문 단건 조회
    public OrderDto.Response getOrder(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return toResponse(order);
    }

    // ✅ 고객별 주문 내역 조회
    public List<OrderDto.Response> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(this::toResponse)
                .toList();
    }

    // ✅ 가게별 주문 내역 조회
    public List<OrderDto.Response> getOrdersByStore(Long storeId) {
        return orderRepository.findByStoreId(storeId).stream()
                .map(this::toResponse)
                .toList();
    }

    // 공통 변환 메서드
    private OrderDto.Response toResponse(Order order) {
        List<OrderDto.OrderItemResponse> items = order.getItems().stream()
                .map(i -> new OrderDto.OrderItemResponse(
                        i.getMenu().getId(),
                        i.getMenu().getName(),
                        i.getQuantity(),
                        i.getPrice()))
                .toList();

        return new OrderDto.Response(
                order.getId(),
                order.getCustomer().getId(),
                order.getStore().getId(),
                order.getTotalPrice(),
                items
        );
    }
}
