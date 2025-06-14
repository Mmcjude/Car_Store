package lv.venta.repo;

import lv.venta.model.Engine;
import lv.venta.model.enums.FuelType;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface IEngineRepo extends CrudRepository<Engine, Long> {
    Engine findByCarId(long carId);
    ArrayList<Engine> findByFuelType(FuelType fuelType);
    ArrayList<Engine> findByHorsepowerGreaterThanEqual(int minHorsepower);
}