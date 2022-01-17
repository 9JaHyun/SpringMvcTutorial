package common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Comment;
import common.CommentDao;
import common.CommentInsertDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommentServlet implements Controller{
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String cmd = request.getParameter("cmd");
        CommentDao dao = new CommentDao();
        if (cmd.equals("selectAll")) {
            List<Comment> comments = dao.findAll();
            String json = objectMapper.writeValueAsString(comments);
            response.setContentType("text/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } else if (cmd.equals("save")) {
            CommentInsertDto dto = new CommentInsertDto();
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/json;charset=utf-8");
            dto.setName(request.getParameter("name"));
            dto.setContent(request.getParameter("content"));
            int id = dao.add(dto);
            response.getWriter().write(id);
        }else if (cmd.equals("delete")) {
            dao.deleteById(Integer.parseInt(request.getParameter("id")));
        }else if (cmd.equals("update")) {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/json;charset=utf-8");
            Comment comment = new Comment();
            comment.setId(Integer.parseInt(request.getParameter("id")));
            comment.setName(request.getParameter("name"));
            comment.setContent(request.getParameter("content"));
            dao.update(comment);
            response.getWriter().write(request.getParameter("id"));
        }
    }
}
