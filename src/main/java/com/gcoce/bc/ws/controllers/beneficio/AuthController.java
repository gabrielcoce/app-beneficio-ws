package com.gcoce.bc.ws.controllers.beneficio;

import com.gcoce.bc.ws.payload.request.LoginRequest;
import com.gcoce.bc.ws.payload.request.SignupRequest;
import com.gcoce.bc.ws.services.beneficio.AuthSvc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "auth-controller", description = "Authentication Services")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final AuthSvc authSvc;

    public AuthController(AuthSvc authSvc) {
        this.authSvc = authSvc;
    }

    @Operation(summary = "Authentication User", description = "Return jwt token")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        return authSvc.authenticateUserSvc(loginRequest);
    }

    @Operation(summary = "Authentication User Creation", description = "Create user authentication in the system")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
        return authSvc.registerUserSvc(signUpRequest);
    }
}
