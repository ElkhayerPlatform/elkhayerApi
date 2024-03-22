package ma.youcode.elkhayer.services;

import lombok.RequiredArgsConstructor;
import ma.youcode.elkhayer.models.Comment;
import ma.youcode.elkhayer.models.dtos.commentDto.CommentDto;
import ma.youcode.elkhayer.repositories.CommentRepository;
import ma.youcode.elkhayer.services.ServiceInterfaces.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);

        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsByUserIdAndPostId(Long userId, Long postId) {
        List<Comment> comments = commentRepository.getCommentsByUserIdAndPostId(userId, postId);
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
    }

    @Override
    public ResponseEntity<CommentDto> deleteComment(Long id) {
        commentRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setId(id);
        comment = commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }
}
