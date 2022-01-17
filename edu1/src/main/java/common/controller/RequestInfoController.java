package common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class RequestInfoController implements Controller {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        out.println("<h2>요청정보</h2>");
        out.println("<h3>요청 컨텐츠: [" + request.getContentType() + "]</h3>");
        out.println("<h3>요청 메서드: [" + request.getMethod() + "]</h3>");
        out.println("<h3>요청 url: [" + request.getRequestURI() + "]</h3>");
        out.println("<h3>요청 세션: [" + request.getSession() + "]</h3>");

    }
}
