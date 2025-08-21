package alp.highorder.customer.api.service;

import alp.highorder.customer.api.dto.CustomerDto;
import alp.highorder.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
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

    @Override
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

    @Override
    public CustomerDto.Response updateCustomer(Long id, CustomerDto.UpdateRequest request) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 이름은 변경 불가 → password, nickname만 수정
        if (request.password() != null) {
            customer.setPassword(request.password());
        }
        if (request.nickname() != null) {
            customer.setNickname(request.nickname());
        }

        var updated = customerRepository.save(customer);

        return new CustomerDto.Response(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getNickname()
        );
    }
}
