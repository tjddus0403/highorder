package alp.highorder.review.domain.entity;

import alp.highorder.customer.domain.entity.Customer;
import alp.highorder.order.domain.entity.OrderItem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews", schema = "jpa")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 누가 작성했는지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // 어떤 주문 메뉴에 대한 리뷰인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    private int rating;              // 평점 (예: 1~5)
    private String comment;          // 리뷰 내용
    private LocalDateTime createdAt; // 최초 작성 시간
    private LocalDateTime updatedAt; // 마지막 수정 시간
}
