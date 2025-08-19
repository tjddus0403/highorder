package alp.highorder.menu.domain.entity;

import alp.highorder.store.domain.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menus", schema = "jpa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    private Double avgRating;     // 평균 평점
    private Integer reviewCount;  // 리뷰 개수
}
