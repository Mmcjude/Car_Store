package lv.venta;

import java.util.Arrays;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.model.*;
import lv.venta.model.enums.*;
import lv.venta.repo.*;

@SpringBootApplication
public class CarStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner initUsers(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User();
            user.setUsername("Martin");
            user.setPassword(passwordEncoder.encode("admin1"));
            user.setRole("ROLE_USER");
            userRepo.save(user);
        };
    }

    @Bean
    public CommandLineRunner testModel(
            ICarRepo carRepo, IEngineRepo engineRepo,
            ICustomerRepo customerRepo, IOrderRepo orderRepo,
            IManufacturerRepo manufacturerRepo, IOrderItemRepo orderItemRepo) {
        return args -> {

            Manufacturer toyota = new Manufacturer("Toyota", "Japan", 1937);
            Manufacturer bmw = new Manufacturer("BMW", "Germany", 1916);
            Manufacturer benz = new Manufacturer("Benz", "Germany", 1883);
            manufacturerRepo.saveAll(Arrays.asList(toyota, bmw, benz));

            Car camry = new Car("Camry", 2023, "Silver", 25000f, "T22ABCDEFGHIJKLMN", BodyType.SEDAN, toyota);
            Car amg = new Car("AMG", 2023, "Blue", 60000f, "B33LAMGAMGAMGAMGA", BodyType.SEDAN, benz);
            Car x5 = new Car("X5", 2023, "Black", 40000f, "B55PLMNOPQRSTUVWX", BodyType.SUV, bmw);
            Car modelX = new Car("ModelX", 2024, "Red", 30000f, "1HGCM82633A004352", BodyType.SUV, toyota);

            carRepo.saveAll(Arrays.asList(camry, amg, x5, modelX));

            Engine camryEngine = new Engine("2AR", 2000, 150, 200, FuelType.GASOLINE, camry);
            Engine amgEngine = new Engine("M177", 4000, 603, 850, FuelType.GASOLINE, amg);
            Engine x5Engine = new Engine("B58", 3000, 300, 400, FuelType.GASOLINE, x5);

            engineRepo.saveAll(Arrays.asList(camryEngine, amgEngine, x5Engine));

            Customer bobby = new Customer("Bobby", "Brown", "123456-12345", "Riga", "bobby@example.com", "12345678");
            Customer michael = new Customer("Michael", "Bay", "654321-54321", "Liepaja", "michael@example.com", "87654321");

            bobby = customerRepo.save(bobby);
            michael = customerRepo.save(michael);

            Order order1 = new Order(LocalDate.now(), bobby);
            OrderItem item1 = new OrderItem(camry, 1, camry.getPrice());
            OrderItem item2 = new OrderItem(amg, 1, amg.getPrice());

            order1.addItem(item1);
            order1.addItem(item2);

            orderRepo.save(order1); // saves items too due to cascade

        };
    }
}
