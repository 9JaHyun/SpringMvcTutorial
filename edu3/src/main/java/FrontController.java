import common.MyView;
import controller.BulletinFormController;
import controller.BulletinListController;
import controller.BulletinSelectController;
import controller.Controller;
import controller.MainController;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    private Map<String, Controller> controllerMap = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap = new HashMap<>();
        controllerMap.put("/edu3_war_exploded/main.do", new MainController());
        controllerMap.put("/edu3_war_exploded/bulletinList.do", new BulletinListController());
        controllerMap.put("/edu3_war_exploded/bulletinForm.do", new BulletinFormController());
        controllerMap.put("/edu3_war_exploded/bulletinSelect.do", new BulletinSelectController());
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

        String viewName = controller.execute(paramMap, model);

        MyView view = viewResolver(viewName);
        view.render(model, request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        Enumeration<String> params = request.getParameterNames();
        request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/" + viewName);
    }
}
