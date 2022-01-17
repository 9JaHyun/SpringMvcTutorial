package common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentInsertDto {
    private String name;
    private String content;
}
