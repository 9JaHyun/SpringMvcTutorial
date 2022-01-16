package controllerV2.controller;

import controllerV2.ControllerV2;
import controllerV2.MyView;
import domain.Comments;
import domain.CommentsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommentsListControllerV2 implements ControllerV2 {
    private CommentsDao commentsDao = new CommentsDao();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Comments> comments = commentsDao.findAll();
        request.setAttribute("comments", comments);

        return new MyView("/comments/commentsList.jsp");
    }
}
