package lv.venta.repo;

import java.util.ArrayList;
import lv.venta.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface IOrderItemRepo extends CrudRepository<OrderItem, Long> {
    ArrayList<OrderItem> findByOrderId(long orderId);
    ArrayList<OrderItem> findByCarId(long carId);
}