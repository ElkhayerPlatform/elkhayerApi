package ma.youcode.elkhayer.models.dtos.userDto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
