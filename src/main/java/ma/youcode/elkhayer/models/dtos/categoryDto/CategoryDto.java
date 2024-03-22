package ma.youcode.elkhayer.models.dtos.categoryDto;

import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String image;
}
