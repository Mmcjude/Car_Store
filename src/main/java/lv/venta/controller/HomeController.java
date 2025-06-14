package lv.venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // GET / - Display the homepage for CHan Export
    @GetMapping("/")
    public String homePage() {
        return "home"; 
    }
}
