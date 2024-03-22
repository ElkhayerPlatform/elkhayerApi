package ma.youcode.elkhayer.services.ServiceInterfaces;

import ma.youcode.elkhayer.models.dtos.categoryDto.CategoryDto;

import java.util.List;


public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getCategories();

    void deleteCategory(Long id);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
}
