package alp.highorder.store.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stores", schema = "jpa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    private String description;
    private String address;
    private Double latitude;
    private Double longitude;
    private String phone;
}
