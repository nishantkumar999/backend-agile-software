package AgileProject.Management.Services;

import AgileProject.Management.DTOs.AuthRequest;
import AgileProject.Management.DTOs.AuthResponse;
import AgileProject.Management.DTOs.OrganizationDto;
import AgileProject.Management.Entities.Organization;
import AgileProject.Management.Entities.User;
import AgileProject.Management.Repository.OrganizationRepository;
import AgileProject.Management.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    public AuthResponse registerOrganization(OrganizationDto organizationDto) {
        if (organizationDto.getName() == null || organizationDto.getName().trim().isEmpty()) {
            return new AuthResponse(null, "Organization name cannot be null or empty!");
        }

        Organization organization = new Organization();
        organization.setName(organizationDto.getName());
        organization.setDomain(organizationDto.getDomain());
        organizationRepository.save(organization);

        return new AuthResponse(null, "Organization registered successfully!");
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                return new AuthResponse(null, "Login successful!");
            } else {
                return new AuthResponse(null, "Incorrect password!");
            }
        }
        return new AuthResponse(null, "User not found!");
    }
}
