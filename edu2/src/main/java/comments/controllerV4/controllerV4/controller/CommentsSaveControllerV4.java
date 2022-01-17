package comments.controllerV4.controllerV4.controller;

import comments.controllerV4.controllerV4.ControllerV4;
import comments.domain.comment.Comments;
import comments.domain.comment.CommentsDao;
import comments.domain.comment.dto.CommentSaveRequestDto;

import java.util.Map;

public class CommentsSaveControllerV4 implements ControllerV4 {
    private CommentsDao commentsDao = new CommentsDao();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String name = paramMap.get("name");
        String content = paramMap.get("content");

        CommentSaveRequestDto dto = new CommentSaveRequestDto(name, content);
        Comments comments = commentsDao.save(dto);

        model.put("comments", comments);

        return "commentsList";
    }
}
