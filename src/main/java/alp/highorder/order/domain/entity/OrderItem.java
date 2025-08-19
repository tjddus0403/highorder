package alp.highorder.order.domain.entity;

import alp.highorder.menu.domain.entity.Menu;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items", schema = "jpa")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    private Integer quantity;
    private Integer price; // (quantity * menu.price)
}
