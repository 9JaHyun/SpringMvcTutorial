package comments.controllerV1.controller;

import comments.controllerV1.ControllerV1;
import comments.domain.comment.Comments;
import comments.domain.comment.CommentsDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommentsListControllerV1 implements ControllerV1 {
    private CommentsDao commentsDao = new CommentsDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Comments> comments = commentsDao.findAll();
        request.setAttribute("comments", comments);

        String path = "/comments/commentsList.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
