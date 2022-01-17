package comments.controllerV1.controller;

import comments.controllerV1.ControllerV1;
import comments.domain.comment.Comments;
import comments.domain.comment.CommentsDao;
import comments.domain.comment.dto.CommentSaveRequestDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentsSaveControllerV1 implements ControllerV1 {
    private CommentsDao commentsDao = new CommentsDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String content = request.getParameter("content");

        CommentSaveRequestDto dto = new CommentSaveRequestDto(name, content);
        Comments comments = commentsDao.save(dto);

        request.setAttribute("comment", comments);
        String path = "/comments/commentsList.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
