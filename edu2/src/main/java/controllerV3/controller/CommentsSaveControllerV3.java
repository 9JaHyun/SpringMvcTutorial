package controllerV3.controller;

import controllerV3.ControllerV3;
import controllerV3.ModelView;
import domain.Comments;
import domain.CommentsDao;
import domain.dto.CommentSaveRequestDto;

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
