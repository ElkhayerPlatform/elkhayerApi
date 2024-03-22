package ma.youcode.elkhayer.controllers;

import ma.youcode.elkhayer.models.dtos.categoryDto.CategoryDto;
import ma.youcode.elkhayer.models.dtos.commentDto.CommentDto;
import ma.youcode.elkhayer.services.ServiceInterfaces.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/user/{userId}/post/{postId}")
    public List<CommentDto> getCommentsBlongToUserInPost(@PathVariable Long userId, @PathVariable Long postId){

        return commentService.getCommentsByUserIdAndPostId(userId, postId);
    }

    @PostMapping("create")
    public CommentDto createComment(@RequestBody CommentDto commentDto){

        return commentService.createComment(commentDto);
    }

    @PutMapping("/{id}")
    public CommentDto updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto){

        return commentService.updateComment(id, commentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id){

        return commentService.deleteComment(id);
    }
}
