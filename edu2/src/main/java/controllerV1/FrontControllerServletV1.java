package controllerV1;

import controllerV1.controller.CommentsSaveControllerV1;
import controllerV1.controller.CommentsDeleteControllerV1;
import controllerV1.controller.CommentsFormControllerV1;
import controllerV1.controller.CommentsListControllerV1;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 모든 컨트롤러의 제어 역할
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerMap = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap = new HashMap<>();
        controllerMap.put("/edu2_war_exploded/front-controller/v1/comments/new-form", new CommentsFormControllerV1());
        controllerMap.put("/edu2_war_exploded/front-controller/v1/comments/save", new CommentsSaveControllerV1());
        controllerMap.put("/edu2_war_exploded/front-controller/v1/comments", new CommentsListControllerV1());
        controllerMap.put("/edu2_war_exploded/front-controller/v1/comments/delete", new CommentsDeleteControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }
}
