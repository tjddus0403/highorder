package alp.highorder.customer.domain.repository;

import alp.highorder.customer.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    
    private final CustomerJpaRepository customerJpaRepository;
    
    @Override
    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        // Spring Data JPA의 메서드 이름 규칙을 사용하여 구현
        return customerJpaRepository.findAll().stream()
                .filter(customer -> customer.getEmail().equals(email) && 
                                   customer.getPassword().equals(password))
                .findFirst();
    }
    
    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findById(id);
    }
    
    @Override
    public Customer save(Customer customer) {
        return customerJpaRepository.save(customer);
    }
}
