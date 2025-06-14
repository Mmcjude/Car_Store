package lv.venta.service;

import lv.venta.model.*;
import java.util.ArrayList;
import java.util.List;

public interface ICarShopService {
    // Order-related methods
    ArrayList<Order> selectAllOrders() throws Exception;
    ArrayList<Order> selectAllOrdersByCustomerId(long customerId) throws Exception;
    ArrayList<Order> selectAllOrdersByCustomerPersonalCode(String personalCode) throws Exception;
    ArrayList<Order> selectAllOrdersForToday() throws Exception;
    Order selectOrderById(long id) throws Exception;
    void insertNewOrder(long customerId, Order order) throws Exception;
    void changeCarInOrder(long orderId, long newCarId) throws Exception;
    
    // Customer-related methods
    List<Customer> selectAllCustomers() throws Exception;
    Customer selectCustomerById(long id) throws Exception;
    void saveCustomer(Customer customer) throws Exception;
    ArrayList<Customer> selectAllCustomersFromCity(String city) throws Exception;
    
    // Car-related methods
    ArrayList<Car> selectAllCarsByPriceRange(float minPrice, float maxPrice) throws Exception;
    int calculateHowManyCustomersForCarType(String carType) throws Exception;
    
    // Helper method
    float calculateOrderTotal(Order order);
}