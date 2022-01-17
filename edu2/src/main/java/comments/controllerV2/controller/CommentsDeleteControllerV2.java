package comments.controllerV2.controller;

import comments.controllerV2.ControllerV2;
import comments.controllerV2.MyView;
import comments.domain.comment.CommentsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentsDeleteControllerV2 implements ControllerV2 {
    private CommentsDao commentsDao = new CommentsDao();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        commentsDao.deleteById(id);
        return new MyView("/edu2_war_exploded/comments/commentsList.jsp");
    }
}
