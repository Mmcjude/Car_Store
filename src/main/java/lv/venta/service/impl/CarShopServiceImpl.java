package lv.venta.service.impl;

import lv.venta.model.*;
import lv.venta.model.enums.OrderStatus;
import lv.venta.repo.*;
import lv.venta.service.ICarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CarShopServiceImpl implements ICarShopService {

    @Autowired private IOrderRepo orderRepo;
    @Autowired private ICustomerRepo customerRepo;
    @Autowired private ICarRepo carRepo;
    @Autowired private IOrderItemRepo orderItemRepo;

    @Override
    public ArrayList<Order> selectAllOrders() throws Exception {
        ArrayList<Order> orders = (ArrayList<Order>) orderRepo.findAll();
        if (orders.isEmpty()) {
            throw new Exception("No orders found");
        }
        return orders;
    }

    @Override
    public ArrayList<Order> selectAllOrdersByCustomerId(long customerId) throws Exception {
        if (customerId <= 0) throw new Exception("Customer ID must be positive");
        if (!customerRepo.existsById(customerId))
            throw new Exception("Customer with ID " + customerId + " not found");
        ArrayList<Order> orders = orderRepo.findByCustomerId(customerId);
        if (orders.isEmpty()) throw new Exception("No orders found for customer ID " + customerId);
        return orders;
    }

    @Override
    public Order selectOrderById(long id) throws Exception {
        if (id <= 0) throw new Exception("Order ID must be positive");
        return orderRepo.findById(id)
                .orElseThrow(() -> new Exception("Order with ID " + id + " not found"));
    }

    @Override
    public ArrayList<Customer> selectAllCustomers() throws Exception {
        ArrayList<Customer> customers = (ArrayList<Customer>) customerRepo.findAll();
        if (customers.isEmpty()) {
            throw new Exception("No customers found");
        }
        return customers;
    }

    @Override
    public Customer selectCustomerById(long id) throws Exception {
        if (id <= 0) throw new Exception("Customer ID must be positive");
        return customerRepo.findById(id)
                .orElseThrow(() -> new Exception("Customer with ID " + id + " not found"));
    }

    @Override
    public void saveCustomer(Customer customer) throws Exception {
        if (customer == null) throw new Exception("Customer cannot be null");
        customerRepo.save(customer);
    }

    @Override
    public void insertNewOrder(long customerId, Order order) throws Exception {
        if (customerId <= 0) throw new Exception("Customer ID must be positive");
        if (order == null) throw new Exception("Order cannot be null");

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new Exception("Customer with ID " + customerId + " not found"));

        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);

        order.recalculateTotal();

        orderRepo.save(order); // will cascade to items
    }

    @Override
    public float calculateOrderTotal(Order order) {
        if (order == null || order.getItems() == null) return 0;
        return order.getItems().stream()
                .map(item -> item.getPrice() * item.getQuantity())
                .reduce(0f, Float::sum);
    }

	@Override
	public ArrayList<Order> selectAllOrdersByCustomerPersonalCode(String personalCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Order> selectAllOrdersForToday() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeCarInOrder(long orderId, long newCarId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Customer> selectAllCustomersFromCity(String city) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Car> selectAllCarsByPriceRange(float minPrice, float maxPrice) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateHowManyCustomersForCarType(String carType) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
