package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lv.venta.model.Car;
import lv.venta.service.ICarCRUDService;
import lv.venta.repo.IManufacturerRepo;  


@Controller
@RequestMapping("/car")
public class CarCRUDController {

    @Autowired
    private ICarCRUDService carService;

    @Autowired
    private IManufacturerRepo manufacturerRepo;  // <--- Add this

    // GET /car/show/all - Show all cars
    @GetMapping("/show/all")
    public String showAllCars(Model model) {
        model.addAttribute("cars", carService.selectAllCars());
        return "car-all-page";
    }

    // GET /car/show/{id} - Show one car by its ID
    @GetMapping("/show/{id}")
    public String showCarById(@PathVariable long id, Model model) {
        try {
            model.addAttribute("car", carService.selectCarById(id));
            return "car-one-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    // GET /car/remove/{id} - Delete a car by its ID
    @GetMapping("/remove/{id}")
    public String deleteCarById(@PathVariable long id, Model model) {
        try {
            carService.deleteCarById(id);
            return "redirect:/car/show/all";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    // GET /car/add - Show the form to add a new car
    @GetMapping("/add")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("manufacturers", manufacturerRepo.findAll());
        return "car-add-page";
    }

    // POST /car/add - Process the form and add a new car
    @PostMapping("/add")
    public String addNewCar(@ModelAttribute("car") Car car, Model model) {
        try {
            carService.insertNewCar(car);
            return "redirect:/car/show/all";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    // GET /car/update/{id} - Show the form to update an existing car
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        try {
            model.addAttribute("car", carService.selectCarById(id));
            return "car-update-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    // POST /car/update/{id} - Process the update form for an existing car
    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable long id, @ModelAttribute("car") Car car, Model model) {
        try {
            carService.updateCarById(id, car);
            return "redirect:/car/show/all";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }
}
