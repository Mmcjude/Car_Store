package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.repo.ICarRepo;
import lv.venta.service.ICarCRUDService;
import lv.venta.model.Car;
import java.util.ArrayList;

@Service
public class CarCRUDServiceImpl implements ICarCRUDService {

    @Autowired
    private ICarRepo carRepo;

    @Override
    public ArrayList<Car> selectAllCars() {
        return (ArrayList<Car>) carRepo.findAll();
    }

    @Override
    public Car selectCarById(long id) throws Exception {
        if(id <= 0) throw new Exception("ID must be positive");
        return carRepo.findById(id).orElseThrow(() -> new Exception("Car not found"));
    }

    @Override
    public void deleteCarById(long id) throws Exception {
        if(!carRepo.existsById(id)) throw new Exception("Car doesn't exist");
        carRepo.deleteById(id);
    }

    @Override
    public void insertNewCar(Car car) throws Exception {
        if(car == null) throw new Exception("Car cannot be null");
        if(carRepo.existsByVin(car.getVin())) throw new Exception("Car with this VIN already exists");
        carRepo.save(car);
    }

    @Override
    public void updateCarById(long id, Car car) throws Exception {
        if(!carRepo.existsById(id)) throw new Exception("Car doesn't exist");
        Car existingCar = carRepo.findById(id).get();
        existingCar.setModel(car.getModel());
        existingCar.setYear(car.getYear());
        existingCar.setPrice(car.getPrice());
        carRepo.save(existingCar);
    }
}