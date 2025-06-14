package lv.venta.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import lv.venta.model.Order;
import lv.venta.model.enums.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface IOrderRepo extends CrudRepository<Order, Long> {
    ArrayList<Order> findByCustomerId(long customerId);
    ArrayList<Order> findByOrderDate(LocalDate date);
    ArrayList<Order> findByStatus(OrderStatus status);
}