package member.controller.SessionTest;

import member.controller.SessionTest.controller.Controller1;
import member.controller.SessionTest.controller.Controller2;
import member.controller.SessionTest.controller.MemberLoginController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "sessionServlet", urlPatterns = "/session/*")
public class SessionServlet extends HttpServlet {
    public Map<String, SessionController> sessionMap = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        sessionMap = new HashMap<>();
        sessionMap.put("/edu2_war_exploded/session/login", new MemberLoginController());
        sessionMap.put("/edu2_war_exploded/session/controller1", new Controller1());
        sessionMap.put("/edu2_war_exploded/session/controller2", new Controller2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println(uri);
        SessionController controller = sessionMap.get(uri);
        controller.execute(req, resp);

    }
}
