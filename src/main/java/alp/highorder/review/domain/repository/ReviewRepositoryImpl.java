package alp.highorder.review.domain.repository;

import alp.highorder.review.domain.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    
    private final ReviewJpaRepository reviewJpaRepository;
    
    @Override
    public List<Review> findByCustomerId(Long customerId) {
        return reviewJpaRepository.findAll().stream()
                .filter(review -> review.getCustomer().getId().equals(customerId))
                .toList();
    }
    
    @Override
    public List<Review> findByOrderItemMenuId(Long menuId) {
        return reviewJpaRepository.findAll().stream()
                .filter(review -> review.getOrderItem().getMenu().getId().equals(menuId))
                .toList();
    }
    
    @Override
    public List<Review> findByOrderItemId(Long orderItemId) {
        return reviewJpaRepository.findAll().stream()
                .filter(review -> review.getOrderItem().getId().equals(orderItemId))
                .toList();
    }
    
    @Override
    public boolean existsByOrderItemIdAndCustomerId(Long orderItemId, Long customerId) {
        return reviewJpaRepository.findAll().stream()
                .anyMatch(review -> review.getOrderItem().getId().equals(orderItemId) &&
                                   review.getCustomer().getId().equals(customerId));
    }
    
    @Override
    public Optional<Review> findById(Long id) {
        return reviewJpaRepository.findById(id);
    }
    
    @Override
    public Review save(Review review) {
        return reviewJpaRepository.save(review);
    }
    
    @Override
    public void delete(Review review) {
        reviewJpaRepository.delete(review);
    }
}
