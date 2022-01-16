package controllerV3.controller;

import controllerV3.ControllerV3;
import controllerV3.ModelView;

import java.util.Map;

public class CommentsFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("/new-form");
    }
}
