package comments.controllerV1.controller;

import comments.controllerV1.ControllerV1;
import comments.domain.comment.CommentsDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentsDeleteControllerV1 implements ControllerV1 {
    private CommentsDao commentsDao = new CommentsDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        commentsDao.deleteById(id);
        String path = "/edu2_war_exploded/comments/commentsList.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
