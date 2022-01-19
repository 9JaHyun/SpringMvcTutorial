package controller;

import common.MyView;
import controller.bulletin.BulletinAddController;
import controller.bulletin.BulletinDeleteController;
import controller.bulletin.BulletinFormController;
import controller.bulletin.BulletinListController;
import controller.bulletin.BulletinSelectController;
import controller.bulletin.BulletinUpdateController;
import controller.Controller;
import controller.MainController;
import controller.notice.NoticeDeleteController;
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
        controllerMap.put("/edu3_war_exploded/bulletinAdd.do", new BulletinAddController());
        controllerMap.put("/edu3_war_exploded/bulletinSelect.do", new BulletinSelectController());
        controllerMap.put("/edu3_war_exploded/bulletinUpdate.do", new BulletinUpdateController());
        controllerMap.put("/edu3_war_exploded/bulletinDelete.do", new BulletinDeleteController());

        controllerMap.put("/edu3_war_exploded/noticeList.do", new NoticeListController());
        controllerMap.put("/edu3_war_exploded/noticeForm.do", new NoticeFormController());
        controllerMap.put("/edu3_war_exploded/noticeAdd.do", new NoticeAddController());
        controllerMap.put("/edu3_war_exploded/noticeSelect.do", new NoticeSelectController());
        controllerMap.put("/edu3_war_exploded/noticeUpdate.do", new NoticeUpdateController());
        controllerMap.put("/edu3_war_exploded/noticeDelete.do", new NoticeDeleteController());
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
