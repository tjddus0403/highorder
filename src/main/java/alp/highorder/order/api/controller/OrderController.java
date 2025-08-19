package alp.highorder.order.api.controller;

import alp.highorder.order.api.dto.OrderDto;
import alp.highorder.order.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 등록
    @PostMapping
    public ResponseEntity<OrderDto.Response> createOrder(@RequestBody OrderDto.CreateRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    // ✅ 주문 단건 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto.Response> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    // ✅ 고객별 주문 내역 조회
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto.Response>> getOrdersByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId));
    }

    // ✅ 가게별 주문 내역 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<OrderDto.Response>> getOrdersByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(orderService.getOrdersByStore(storeId));
    }
}
