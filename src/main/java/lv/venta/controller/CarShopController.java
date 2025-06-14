package lv.venta.controller;

import lv.venta.model.Customer;
import lv.venta.service.ICarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class CarShopController {

    private final ICarShopService shopService;

    @Autowired
    public CarShopController(ICarShopService shopService) {
        this.shopService = shopService;
    }

    // Show form to add customer
    @GetMapping("/customer/add") //http://localhost:8080/shop/customer/add
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-add-page";
    }

    // Handle form submission
    @PostMapping("/customer/add")
    public String addNewCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            shopService.saveCustomer(customer);
            return "redirect:/shop/customers";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "customer-add-page";
        }
    }

    // List all customers
    @GetMapping("/customers")  // http://localhost:8080/shop/customers
    public String listAllCustomers(Model model) {
        try {
            List<Customer> customers = shopService.selectAllCustomers();
            model.addAttribute("customers", customers);
            return "customer-list-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }
}
