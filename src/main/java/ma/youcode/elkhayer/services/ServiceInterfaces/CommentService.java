package ma.youcode.elkhayer.services.ServiceInterfaces;

import ma.youcode.elkhayer.models.dtos.categoryDto.CategoryDto;
import ma.youcode.elkhayer.models.dtos.commentDto.CommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CommentService {
    
    CommentDto createComment(CommentDto commentDto);

    List<CommentDto> getCommentsByUserIdAndPostId(Long userId, Long postId);

    ResponseEntity<CommentDto> deleteComment(Long id);

    CommentDto updateComment(Long id, CommentDto commentDto);
}