package alp.highorder.order.api.service;

import alp.highorder.customer.domain.repository.CustomerRepository;
import alp.highorder.store.domain.repository.StoreRepository;
import alp.highorder.menu.domain.repository.MenuRepository;
import alp.highorder.order.api.dto.OrderDto;
import alp.highorder.order.domain.entity.Order;
import alp.highorder.order.domain.entity.OrderItem;
import alp.highorder.order.domain.repository.OrderItemRepository;
import alp.highorder.order.domain.repository.OrderRepository;
import alp.highorder.stamp.api.service.StampCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final StampCouponService stampCouponService; // ✅ 추가
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderDto.Response createOrder(OrderDto.CreateRequest request) {
        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        var store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Order order = Order.builder()
                .customer(customer)
                .store(store)
                .totalPrice(0)
                .items(new ArrayList<>())  // ✅ 반드시 초기화
                .orderedAt(LocalDateTime.now())
                .build();

        int totalPrice = 0;
        Map<Long, Integer> stampCountMap = new HashMap<>(); // ✅ 가게별 스탬프 수량 누적

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

            Long menuStoreId = menu.getStore().getId();
            // ✅ 메뉴 수량만큼 스탬프 적립
            stampCountMap.put(menuStoreId,
                    stampCountMap.getOrDefault(menuStoreId, 0) + itemReq.quantity());
        }

        order.setTotalPrice(totalPrice);
        Order saved = orderRepository.save(order);

        // ✅ 주문 완료 후 스탬프 적립 실행 (수량만큼 적립)
        for (Map.Entry<Long, Integer> entry : stampCountMap.entrySet()) {
            stampCouponService.addStamp(customer.getId(), entry.getKey(), entry.getValue());
        }

        return toResponse(saved);
    }

    @Override
    public OrderDto.Response getOrder(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return toResponse(order);
    }

    @Override
    public OrderDto.OrderItemResponse getOrderItem(Long orderItemId) {
        var orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        return new OrderDto.OrderItemResponse(
                orderItem.getId(),
                orderItem.getMenu().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }

    @Override
    public List<OrderDto.Response> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<OrderDto.Response> getOrdersByStore(Long storeId) {
        return orderRepository.findByStoreId(storeId).stream()
                .map(this::toResponse)
                .toList();
    }

    private OrderDto.Response toResponse(Order order) {
        List<OrderDto.OrderItemResponse> items = order.getItems().stream()
                .map(i -> new OrderDto.OrderItemResponse(
                        i.getId(),
                        i.getMenu().getId(),
                        i.getQuantity(),
                        i.getPrice()))
                .toList();

        return new OrderDto.Response(
                order.getId(),
                order.getCustomer().getId(),
                order.getStore().getId(),
                order.getTotalPrice(),
                items,
                order.getOrderedAt()  // ✅ 주문 시각 포함
        );
    }
}
