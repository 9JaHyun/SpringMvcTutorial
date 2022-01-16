package controllerV4.controllerV4;

import controllerV4.controllerV4.controller.CommentsDeleteControllerV4;
import controllerV4.controllerV4.controller.CommentsFormControllerV4;
import controllerV4.controllerV4.controller.CommentsListControllerV4;
import controllerV4.controllerV4.controller.CommentsSaveControllerV4;

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
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap = new HashMap<>();
        controllerMap.put("/edu2_war_exploded/front-controller/v4/comments/new-form", new CommentsFormControllerV4());
        controllerMap.put("/edu2_war_exploded/front-controller/v4/comments/save", new CommentsSaveControllerV4());
        controllerMap.put("/edu2_war_exploded/front-controller/v4/comments", new CommentsListControllerV4());
        controllerMap.put("/edu2_war_exploded/front-controller/v4/comments/delete", new CommentsDeleteControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);
        view.render(model, request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/comments/" + viewName + ".jsp");
    }
}
