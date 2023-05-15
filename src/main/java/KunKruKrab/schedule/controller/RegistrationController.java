package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.Registration.RegistrationRequest;
import KunKruKrab.schedule.dto.Registration.RegistrationResponse;
import KunKruKrab.schedule.model.Registration;
import KunKruKrab.schedule.model.User;
import KunKruKrab.schedule.service.RegistrationService;
import KunKruKrab.schedule.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Registration> getAll() {
        return registrationService.getAll();
    }

    @GetMapping("/{id}")
    public List<RegistrationResponse> getRegistrationsByCourseID(@PathVariable UUID id) {
        return registrationService.getRegistrationByCourseID(id);
    }

    @PostMapping
    public String register(@Valid @RequestBody RegistrationRequest registration, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            assert fieldError != null;
            return String.format("registration %s: %s", fieldError.getField(), fieldError.getDefaultMessage());
        }

        User user = userService.getUserByEmail(principal.getName());

        System.out.println("User ID: " + user.getId());

        registration.setUserID(user.getId());

        registrationService.registerToCourse(registration);
        return "Register to course successfully";
    }
}
