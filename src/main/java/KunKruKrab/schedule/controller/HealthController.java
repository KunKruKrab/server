package KunKruKrab.schedule.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public String getHealth(Model model) {
        return "Hello, Welcome to KunKruKrab schedule API";
    }
}
