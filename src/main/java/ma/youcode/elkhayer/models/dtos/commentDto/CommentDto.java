package ma.youcode.elkhayer.models.dtos.commentDto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String comment;
    private String createdAt;
    private Long postId;
    private Long userId;
}