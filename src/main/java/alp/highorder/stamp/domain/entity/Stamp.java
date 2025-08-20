package alp.highorder.stamp.domain.entity;

import java.time.LocalDateTime;

import alp.highorder.customer.domain.entity.Customer;
import alp.highorder.store.domain.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stamps", schema = "jpa")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    private int count;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
