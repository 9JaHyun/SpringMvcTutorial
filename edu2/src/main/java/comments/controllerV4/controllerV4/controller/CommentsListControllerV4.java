package comments.controllerV4.controllerV4.controller;

import comments.controllerV4.controllerV4.ControllerV4;
import comments.domain.comment.Comments;
import comments.domain.comment.CommentsDao;

import java.util.List;
import java.util.Map;

public class CommentsListControllerV4 implements ControllerV4 {
    private CommentsDao commentsDao = new CommentsDao();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Comments> commentsList = commentsDao.findAll();

        model.put("commentsList", commentsList);
        return "commentsList";
    }
}
