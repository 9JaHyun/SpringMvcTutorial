package member.controller;

import member.controller.controller.*;

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
@WebServlet(name = "memberFrontController", urlPatterns = "/front-controller/members/*")
public class FrontControllerServlet extends HttpServlet {
    private Map<String, Controller> controllerMap = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap = new HashMap<>();
        controllerMap.put("/edu2_war_exploded/front-controller/members/new-form", new MemberFormController());
        controllerMap.put("/edu2_war_exploded/front-controller/members/save", new MemberSaveController());
        controllerMap.put("/edu2_war_exploded/front-controller/members", new MemberListController());
        controllerMap.put("/edu2_war_exploded/front-controller/members" + "/update", new MemberUpdateController());
        controllerMap.put("/edu2_war_exploded/front-controller/members" + "/delete", new MemberDeleteController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        Controller controller = controllerMap.get(requestURI);
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
        return new MyView("/members/" + viewName + ".jsp");
    }
}
