package lv.venta.repo;

import java.util.ArrayList;
import lv.venta.model.Customer;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ICustomerRepo extends CrudRepository<Customer, Long> {
    ArrayList<Customer> findByCity(String city);
    Optional<Customer> findByPersonalCode(String personalCode);
    ArrayList<Customer> findByEmailContainingIgnoreCase(String emailPart);
}