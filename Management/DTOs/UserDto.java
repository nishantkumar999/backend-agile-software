package AgileProject.Management.DTOs;


import AgileProject.Management.Entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private  String name;
    private  String email;
    private  String password;
    private Role role;
    private  Long organizationId;
}
