package alp.highorder.order.domain.repository;

import alp.highorder.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {
    
    private final OrderItemJpaRepository orderItemJpaRepository;
    
    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemJpaRepository.findAll().stream()
                .filter(orderItem -> orderItem.getOrder().getId().equals(orderId))
                .toList();
    }
    
    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemJpaRepository.findById(id);
    }
    
    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemJpaRepository.save(orderItem);
    }
}
