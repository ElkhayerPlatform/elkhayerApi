package ma.youcode.elkhayer.models.dtos.postDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.elkhayer.models.dtos.categoryDto.CategoryResponseDto;
import ma.youcode.elkhayer.models.dtos.userDto.UserResponseDto;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String image;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Double donation;

    private UserResponseDto user;
    private CategoryResponseDto category;
}
