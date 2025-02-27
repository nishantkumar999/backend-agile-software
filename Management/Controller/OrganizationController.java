package AgileProject.Management.Controller;

import AgileProject.Management.DTOs.OrganizationDto;
import AgileProject.Management.Entities.Organization;
import AgileProject.Management.Repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class OrganizationController {

    private final OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @PostMapping("/registerorganization")
    public ResponseEntity<String> registerOrganization(@RequestBody OrganizationDto organizationDto) {
        System.out.println("Received Organization: " + organizationDto.getName() + ", " + organizationDto.getDomain());

        // Convert DTO to Entity
        Organization organization = new Organization();
        organization.setName(organizationDto.getName());
        organization.setDomain(organizationDto.getDomain());

        // Save to Database
        organizationRepository.save(organization);

        return ResponseEntity.ok("Organization registered successfully!");
    }
}
