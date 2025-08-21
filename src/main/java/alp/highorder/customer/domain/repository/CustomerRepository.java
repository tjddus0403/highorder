package alp.highorder.customer.domain.repository;

import alp.highorder.customer.domain.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findByEmailAndPassword(String email, String password);
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
}
