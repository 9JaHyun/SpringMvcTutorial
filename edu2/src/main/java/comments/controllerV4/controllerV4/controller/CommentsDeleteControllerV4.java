package comments.controllerV4.controllerV4.controller;

import comments.controllerV4.controllerV4.ControllerV4;
import comments.domain.comment.CommentsDao;

import java.util.Map;

public class CommentsDeleteControllerV4 implements ControllerV4 {
    private CommentsDao commentsDao = new CommentsDao();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        Integer id = Integer.valueOf(paramMap.get("id"));
        commentsDao.deleteById(id);

        return "commentsList";
    }
}
