package ma.youcode.elkhayer.models.dtos.profileDto;

import lombok.Data;
import ma.youcode.elkhayer.models.dtos.userDto.UserResponseDto;

@Data
public class ProfileDto {
    private Long id;
    private String description;
    private String city;
    private String country;
    private String phone;
    private String Address;
    private String website;
    private String facebook;
    private String twitter;
    private String linkedin;
    private String picture;
    private Double capital;

    private UserResponseDto user;
}
