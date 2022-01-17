package comments.controllerV4.controllerV4.controller;

import comments.controllerV4.controllerV4.ControllerV4;

import java.util.Map;

public class CommentsFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
