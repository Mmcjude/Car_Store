package lv.venta.service;

import java.util.ArrayList;
import lv.venta.model.Car;

public interface ICarCRUDService {
    ArrayList<Car> selectAllCars();
    Car selectCarById(long id) throws Exception;
    void deleteCarById(long id) throws Exception;
    void insertNewCar(Car car) throws Exception;
    void updateCarById(long id, Car car) throws Exception;
    
}