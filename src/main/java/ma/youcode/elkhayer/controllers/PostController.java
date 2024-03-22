package ma.youcode.elkhayer.controllers;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.elkhayer.models.dtos.postDto.PostRequestDto;
import ma.youcode.elkhayer.models.dtos.postDto.PostResponseDto;
import ma.youcode.elkhayer.services.ServiceInterfaces.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Page<PostResponseDto> getPosts(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "50") int size,
                                         @RequestParam (defaultValue = "id,desc") String sortBy
                                  ){

        return postService.getPosts(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public PostRequestDto getPostById(@PathVariable Long id){

        return postService.getPostById(id);
    }

    @GetMapping("/user/{id}")
    public List<PostRequestDto> getPostsOfUser(@PathVariable Long id){

        return postService.getPostsOfUser(id);
    }

    @PostMapping("create")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){

        return postService.createPost(postRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return "Post deleted successfully";
        } catch (EntityNotFoundException e) {
            return "Post not found";
        }
    }

}
