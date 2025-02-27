package AgileProject.Management.Controller;

import AgileProject.Management.DTOs.AuthRequest;
import AgileProject.Management.DTOs.AuthResponse;
import AgileProject.Management.DTOs.OrganizationDto;
import AgileProject.Management.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-organization")
    public ResponseEntity<AuthResponse> registerOrganization(@RequestBody OrganizationDto organizationDto) {
        AuthResponse response = authService.registerOrganization(organizationDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.authenticate(authRequest);
        return ResponseEntity.ok(response);
    }
}
