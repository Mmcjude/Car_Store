package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lv.venta.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "items")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private float totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order(LocalDate orderDate, Customer customer) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.status = OrderStatus.PENDING;
        this.totalPrice = 0f;
    }

    public void addItem(OrderItem item) {
        if (!items.contains(item)) {
            item.setOrder(this); // important: set back-reference
            items.add(item);
            totalPrice += item.getPrice() * item.getQuantity();
        }
    }

    public void recalculateTotal() {
        totalPrice = (float) items.stream()
            .mapToDouble(i -> i.getPrice() * i.getQuantity())
            .sum();
    }
}
