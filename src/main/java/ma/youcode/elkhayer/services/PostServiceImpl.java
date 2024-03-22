package ma.youcode.elkhayer.services;

import lombok.RequiredArgsConstructor;
import ma.youcode.elkhayer.exceptions.ApiRequestException;
import ma.youcode.elkhayer.models.Category;
import ma.youcode.elkhayer.models.Post;
import ma.youcode.elkhayer.models.User;
import ma.youcode.elkhayer.models.dtos.categoryDto.CategoryResponseDto;
import ma.youcode.elkhayer.models.dtos.postDto.PostRequestDto;
import ma.youcode.elkhayer.models.dtos.postDto.PostResponseDto;
import ma.youcode.elkhayer.models.dtos.userDto.UserResponseDto;
import ma.youcode.elkhayer.repositories.CategoryRepository;
import ma.youcode.elkhayer.repositories.PostRepository;
import ma.youcode.elkhayer.repositories.UserRepository;
import ma.youcode.elkhayer.services.ServiceInterfaces.PostService;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<PostResponseDto> getPosts(int page, int pageSize, String sortBy) {
        String[] sortParams = sortBy.split(",");
        String sortField = sortParams[0];
        Sort.Direction sortDirection = Sort.Direction.ASC;

        if (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])) {
            sortDirection = Sort.Direction.DESC;
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortDirection, sortField));
        Page<Post> posts = postRepository.findAll(pageRequest);

        return posts.map(post -> {
            PostResponseDto postResponseDto = modelMapper.map(post, PostResponseDto.class);

            if (post.getUser() != null) {
                UserResponseDto userResponseDto = modelMapper.map(post.getUser(), UserResponseDto.class);
                postResponseDto.setUser(userResponseDto);
            }

            if (post.getCategory() != null) {
                CategoryResponseDto categoryResponseDto = modelMapper.map(post.getCategory(), CategoryResponseDto.class);
                postResponseDto.setCategory(categoryResponseDto);
            }

            return postResponseDto;
        });
    }

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        if (postRequestDto.getUserId() == null) {
            throw new ApiRequestException("User id is required");
        }

        if (!userRepository.existsById(postRequestDto.getUserId())) {
            throw new ApiRequestException("User not found");
        }

        User user = userRepository.findById(postRequestDto.getUserId()).orElse(null);
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);

        Category category = null;
        CategoryResponseDto categoryResponseDto = null;

        if (postRequestDto.getCategoryId() != null) {
            category = categoryRepository.findById(postRequestDto.getCategoryId()).orElse(null);
            if (category == null) {
                throw new ApiRequestException("Category not found");
            }
            categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
        }

        Post post = modelMapper.map(postRequestDto, Post.class);
        post.setCategory(category);

        PostResponseDto postResponseDto = modelMapper.map(postRepository.save(post), PostResponseDto.class);
        postResponseDto.setUser(userResponseDto);
        postResponseDto.setCategory(categoryResponseDto);

        return postResponseDto;
    }


    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostRequestDto getPostById(Long id) {
        return modelMapper.map(postRepository.findById(id).orElse(null), PostRequestDto.class);
    }

    @Override
    public List<PostRequestDto> getPostsOfUser(Long id) {
        List<Post> posts = postRepository.findAllByUserId(id);

        if (posts.isEmpty()) {
            throw new ApiRequestException("No posts found for this user");
        }

        return posts.stream().map(post -> modelMapper.map(post, PostRequestDto.class)).toList();
    }
}
