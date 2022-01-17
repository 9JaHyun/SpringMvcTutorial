package common;

import common.controller.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    Map<String, Controller> list = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("servletINIT");
        list = new HashMap<>();
        list.put("/home.do", new HomeController());
        list.put("/login.do", new LoginController());
        list.put("/memberList.do", new MemberListController());
        list.put("/requestInfo.do", new RequestInfoController());
        list.put("/comments.do", new CommentServlet());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());
        System.out.println(path);
        Controller controller = list.get(path);
        try {
            controller.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
