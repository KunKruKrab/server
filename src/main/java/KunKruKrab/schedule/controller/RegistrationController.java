package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.Registration.RegistrationRequest;
import KunKruKrab.schedule.dto.Registration.RegistrationResponse;
import KunKruKrab.schedule.model.Registration;
import KunKruKrab.schedule.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<Registration> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public List<RegistrationResponse> getRegistrationsByCourseID(@PathVariable UUID id) {
        return service.getRegistrationByCourseID(id);
    }

    @PostMapping
    public String register(@Valid @RequestBody RegistrationRequest registration, BindingResult result) {

        if (result.hasErrors()) {
            // return new ResponseEntity<String>("Invalid request format", HttpStatus.UNPROCESSABLE_ENTITY);
            FieldError fieldError = result.getFieldError();
            String error = String.format("registration %s: %s", fieldError.getField(), fieldError.getDefaultMessage());
            return error;
        }
        service.registerToCourse(registration);
        return "Register to course successfully";
//        return new ResponseEntity<RegistrationRequest>(registration, HttpStatus.OK);
    }
}
