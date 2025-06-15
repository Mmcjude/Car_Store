package lv.venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // GET / - displays the homepage for my garage
    @GetMapping("/")
    public String homePage() {
        return "home"; 
    }
}
