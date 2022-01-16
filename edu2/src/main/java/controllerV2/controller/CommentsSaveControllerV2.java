package controllerV2.controller;

import controllerV2.ControllerV2;
import controllerV2.MyView;
import domain.Comments;
import domain.CommentsDao;
import domain.dto.CommentSaveRequestDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentsSaveControllerV2 implements ControllerV2 {
    private CommentsDao commentsDao = new CommentsDao();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String content = request.getParameter("content");

        CommentSaveRequestDto dto = new CommentSaveRequestDto(name, content);
        Comments comments = commentsDao.save(dto);

        request.setAttribute("comment", comments);
        return new MyView("/comments/commentsList.jsp");
    }
}
