package controllerV3;

import controllerV3.controller.CommentsDeleteControllerV3;
import controllerV3.controller.CommentsFormControllerV3;
import controllerV3.controller.CommentsListControllerV3;
import controllerV3.controller.CommentsSaveControllerV3;

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
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap = new HashMap<>();
        controllerMap.put("/edu2_war_exploded/front-controller/v3/comments/new-form", new CommentsFormControllerV3());
        controllerMap.put("/edu2_war_exploded/front-controller/v3/comments/save", new CommentsSaveControllerV3());
        controllerMap.put("/edu2_war_exploded/front-controller/v3/comments", new CommentsListControllerV3());
        controllerMap.put("/edu2_war_exploded/front-controller/v3/comments/delete", new CommentsDeleteControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/comments" + viewName + ".jsp");
    }
}
