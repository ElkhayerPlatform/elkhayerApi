package ma.youcode.elkhayer.services;

import lombok.RequiredArgsConstructor;
import ma.youcode.elkhayer.models.Category;
import ma.youcode.elkhayer.models.dtos.categoryDto.CategoryDto;
import ma.youcode.elkhayer.repositories.CategoryRepository;
import ma.youcode.elkhayer.services.ServiceInterfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category = categoryRepository.save(category);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setId(id);
        category = categoryRepository.save(category);
        return modelMapper.map(category, CategoryDto.class);
    }
}
