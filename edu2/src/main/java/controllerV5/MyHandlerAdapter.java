package controllerV5;

import controllerV3.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {
    boolean isSupports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler);
}
