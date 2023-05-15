package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.Auth.AuthResponse;
import KunKruKrab.schedule.dto.Auth.LoginRequest;
import KunKruKrab.schedule.dto.Auth.RegisterRequest;
import KunKruKrab.schedule.service.AuthService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private AuthService authService;

    private final Bucket bucket;

    public AuthController() {
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate (@RequestBody LoginRequest request){
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(authService.login(request));
        }
       return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
