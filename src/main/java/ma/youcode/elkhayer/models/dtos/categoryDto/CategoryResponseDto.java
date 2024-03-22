package ma.youcode.elkhayer.models.dtos.categoryDto;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private String image;
}
