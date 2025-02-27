package AgileProject.Management.DTOs;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationDto {

    @NotBlank(message = "Organization name cannot be empty")

    private  String name;
        private  String domain;

        public  OrganizationDto(){

        }

    public OrganizationDto(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
