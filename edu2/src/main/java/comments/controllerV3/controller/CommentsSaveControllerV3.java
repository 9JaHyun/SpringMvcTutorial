package comments.controllerV3.controller;

import comments.controllerV3.ControllerV3;
import comments.controllerV3.ModelView;
import comments.domain.comment.Comments;
import comments.domain.comment.CommentsDao;
import comments.domain.comment.dto.CommentSaveRequestDto;

import java.util.Map;

public class CommentsSaveControllerV3 implements ControllerV3 {
    private CommentsDao commentsDao = new CommentsDao();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String name = paramMap.get("name");
        String content = paramMap.get("content");

        CommentSaveRequestDto dto = new CommentSaveRequestDto(name, content);
        Comments comments = commentsDao.save(dto);

        ModelView mv = new ModelView("/commentsList");
        mv.getModel().put("comments", comments);
        return mv;
    }
}
