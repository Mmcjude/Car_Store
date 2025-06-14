package lv.venta.repo;

import java.util.ArrayList;
import lv.venta.model.Car;
import lv.venta.model.enums.BodyType;
import org.springframework.data.repository.CrudRepository;

public interface ICarRepo extends CrudRepository<Car, Long> {
    ArrayList<Car> findByManufacturerId(long manufacturerId);
    ArrayList<Car> findByPriceBetween(float minPrice, float maxPrice);
    ArrayList<Car> findByBodyType(BodyType bodyType);
    boolean existsByVin(String vin);
}