package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"order", "car"})
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @NotNull
    private Car car;

    @Column(nullable = false)
    @Min(1)
    private int quantity;

    @Column(nullable = false)
    @Min(0)
    private float price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderItem(Car car, int quantity, float price) {
        this.car = car;
        this.quantity = quantity;
        this.price = price;
    }
}
