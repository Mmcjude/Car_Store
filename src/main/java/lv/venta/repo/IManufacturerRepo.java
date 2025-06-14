package lv.venta.repo;

import java.util.List;
import lv.venta.model.Manufacturer;
import org.springframework.data.repository.CrudRepository;

public interface IManufacturerRepo extends CrudRepository<Manufacturer, Long> {

    List<Manufacturer> findByCountry(String country);

    List<Manufacturer> findByNameContainingIgnoreCase(String namePart);

    boolean existsByName(String name);
}
