package comments.controllerV3.controller;

import comments.controllerV3.ControllerV3;
import comments.controllerV3.ModelView;
import comments.domain.comment.CommentsDao;

import java.util.Map;

public class CommentsDeleteControllerV3 implements ControllerV3 {
    private CommentsDao commentsDao = new CommentsDao();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        Integer id = Integer.valueOf(paramMap.get("id"));
        commentsDao.deleteById(id);

        return new ModelView("/commentsList");
    }
}
