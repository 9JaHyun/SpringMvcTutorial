package comments.domain.comment.dto;

import comments.domain.comment.Comments;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentSaveRequestDto {
    private String name;
    private String content;

    public Comments toEntity(Integer id) {
        return new Comments(id, name, content);
    }
}
