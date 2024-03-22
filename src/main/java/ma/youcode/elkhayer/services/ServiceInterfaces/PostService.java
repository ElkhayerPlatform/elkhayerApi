package ma.youcode.elkhayer.services.ServiceInterfaces;

import ma.youcode.elkhayer.models.dtos.postDto.PostRequestDto;
import ma.youcode.elkhayer.models.dtos.postDto.PostResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PostService {
    Page<PostResponseDto> getPosts(int page, int size, String sortBy);

    PostResponseDto createPost(PostRequestDto postRequestDto);

    void deletePost(Long id);

    PostRequestDto getPostById(Long id);

    List<PostRequestDto> getPostsOfUser(Long id);
}
