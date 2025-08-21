package alp.highorder.order.domain.repository;

import alp.highorder.order.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    
    private final OrderJpaRepository orderJpaRepository;
    
    @Override
    public List<Order> findByCustomerId(Long customerId) {
        return orderJpaRepository.findAll().stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .toList();
    }
    
    @Override
    public List<Order> findByStoreId(Long storeId) {
        return orderJpaRepository.findAll().stream()
                .filter(order -> order.getStore().getId().equals(storeId))
                .toList();
    }
    
    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id);
    }
    
    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }
}
