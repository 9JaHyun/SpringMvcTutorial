package comments.controllerV3.controller;

import comments.controllerV3.ControllerV3;
import comments.controllerV3.ModelView;
import comments.domain.comment.Comments;
import comments.domain.comment.CommentsDao;

import java.util.List;
import java.util.Map;

public class CommentsListControllerV3 implements ControllerV3 {
    private CommentsDao commentsDao = new CommentsDao();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Comments> commentsList = commentsDao.findAll();

        ModelView mv = new ModelView("/commentsList");
        mv.getModel().put("commentsList", commentsList);
        return mv;
    }
}
