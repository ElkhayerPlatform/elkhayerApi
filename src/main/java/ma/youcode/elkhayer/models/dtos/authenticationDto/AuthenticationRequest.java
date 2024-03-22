package ma.youcode.elkhayer.models.dtos.authenticationDto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
