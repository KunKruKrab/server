package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.Registration.RegisterToCourseRequest;
import KunKruKrab.schedule.dto.Registration.RegistrationRequest;
import KunKruKrab.schedule.dto.Registration.RegistrationResponse;
import KunKruKrab.schedule.model.Registration;
import KunKruKrab.schedule.model.User;
import KunKruKrab.schedule.service.RegistrationService;
import KunKruKrab.schedule.service.UserService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final Bucket bucket;

    public RegistrationController() {
        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

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
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest request, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            assert fieldError != null;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("registration %s: %s", fieldError.getField(), fieldError.getDefaultMessage()));
        }

        if (!bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        User user = userService.getUserByEmail(principal.getName());

        RegisterToCourseRequest registerToCourse = new RegisterToCourseRequest();
        registerToCourse.setUserID(user.getId());
        registerToCourse.setClassCode(request.getClassCode());

        boolean isRegistered = registrationService.registerToCourse(registerToCourse);

        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("You have already registered for this course!");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Register to course successfully");
    }
}
