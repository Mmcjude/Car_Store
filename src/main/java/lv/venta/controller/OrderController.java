package lv.venta.controller;

import lv.venta.model.Order;
import lv.venta.service.ICarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/orders") // Base URL: http://localhost:8080/orders
public class OrderController {

    @Autowired
    private ICarShopService carShopService;

    // Show all orders page
    @GetMapping
    public String showAllOrders(Model model) {
        try {
            model.addAttribute("orders", carShopService.selectAllOrders()); // "orders" for list page
            return "order-history";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    // Show order details page by order id
    @GetMapping("/details/{id}") // http://localhost:8080/orders/details/1
    public String showOrderDetails(@PathVariable long id, Model model) {
        try {
            model.addAttribute("order", carShopService.selectOrderById(id)); // "order" for details page
            return "order-details";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/orders";  // redirect to list on error
        }
    }

    // Show form to add a new order
    @GetMapping("/add")  //http://localhost:8080/orders/add
    public String showAddOrderForm(Model model) {
        try {
            Order order = new Order();
            order.setOrderDate(LocalDate.now());  // default order date = today
            model.addAttribute("order", order);  
            model.addAttribute("customers", carShopService.selectAllCustomers());
            return "order-add-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/orders";
        }
    }

    // Process form submission to add new order
    @PostMapping("/add")
    public String processAddOrder(@ModelAttribute Order order,
                                  @RequestParam long customerId,
                                  Model model) {
        try {
            carShopService.insertNewOrder(customerId, order);
            return "redirect:/orders";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("order", order);
            try {
                model.addAttribute("customers", carShopService.selectAllCustomers());
            } catch (Exception ex) {
                model.addAttribute("error", ex.getMessage());
            }
            return "order-add";
        }
    }
}
