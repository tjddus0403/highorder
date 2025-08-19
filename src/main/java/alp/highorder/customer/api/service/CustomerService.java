package alp.highorder.customer.api.service;

import alp.highorder.customer.api.dto.CustomerDto;
import alp.highorder.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerDto.Response login(CustomerDto.LoginRequest request) {
        var customer = customerRepository
                .findByEmailAndPassword(request.email(), request.password())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        return new CustomerDto.Response(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getNickname()
        );
    }

    // ✅ 고객 단건 조회
    public CustomerDto.Response getCustomer(Long id) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return new CustomerDto.Response(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getNickname()
        );
    }
}
