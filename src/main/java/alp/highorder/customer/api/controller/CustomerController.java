package alp.highorder.customer.api.controller;

import alp.highorder.customer.api.dto.CustomerDto;
import alp.highorder.customer.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody CustomerDto.LoginRequest request) {
    //     try {
    //         CustomerDto.Response response = customerService.login(request);
    //         return ResponseEntity.ok(response); // ✅ 고객 정보 JSON 반환
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.status(401).body("❌ 잘못된 이메일 또는 비밀번호입니다.");
    //     }
    // }

    @GetMapping("/login")
    public ResponseEntity<?> loginByQuery(
            @RequestParam String email,
            @RequestParam String password) {
        try {
            CustomerDto.Response response = customerService.login(
                    new CustomerDto.LoginRequest(email, password));
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("❌ 잘못된 이메일 또는 비밀번호입니다.");
        }
    }
    
    // ✅ 고객 단건 조회 (id 기준)
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto.Response> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }
}
