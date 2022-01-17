package comments.controllerV5;

import comments.controllerV3.ModelView;
import comments.controllerV3.controller.CommentsFormControllerV3;
import comments.controllerV3.controller.CommentsListControllerV3;
import comments.controllerV3.controller.CommentsSaveControllerV3;
import comments.controllerV4.controllerV4.MyView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 모든 컨트롤러의 제어 역할
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        initHandlerMappingMap();
        initHandlerAdapters();

    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV5.service");
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        ModelView mv = handlerAdapter.handle(request, response, handler);
        MyView myView = viewResolver(mv.getViewName());
        myView.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.isSupports(handler)) {
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/comments/" + viewName + ".jsp");
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/comments/new-form", new CommentsFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/comments/save", new CommentsSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/comments/", new CommentsListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }
}
