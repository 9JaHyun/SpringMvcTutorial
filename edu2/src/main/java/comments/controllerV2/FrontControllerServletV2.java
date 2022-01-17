package comments.controllerV2;

import comments.controllerV2.controller.CommentsDeleteControllerV2;
import comments.controllerV2.controller.CommentsFormControllerV2;
import comments.controllerV2.controller.CommentsListControllerV2;
import comments.controllerV2.controller.CommentsSaveControllerV2;

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
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap = new HashMap<>();
        controllerMap.put("/edu2_war_exploded/front-controller/v2/comments/new-form", new CommentsFormControllerV2());
        controllerMap.put("/edu2_war_exploded/front-controller/v2/comments/save", new CommentsSaveControllerV2());
        controllerMap.put("/edu2_war_exploded/front-controller/v2/comments", new CommentsListControllerV2());
        controllerMap.put("/edu2_war_exploded/front-controller/v2/comments/delete", new CommentsDeleteControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
