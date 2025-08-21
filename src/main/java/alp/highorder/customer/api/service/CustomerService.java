package alp.highorder.customer.api.service;

import alp.highorder.customer.api.dto.CustomerDto;

public interface CustomerService {
    CustomerDto.Response login(CustomerDto.LoginRequest request);
    CustomerDto.Response getCustomer(Long id);
    CustomerDto.Response updateCustomer(Long id, CustomerDto.UpdateRequest request);
}
