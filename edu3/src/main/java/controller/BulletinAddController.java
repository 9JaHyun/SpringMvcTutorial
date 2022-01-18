package controller;

import java.util.Map;

public class BulletinAddController implements Controller{

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        return "bulletin/bulletinForm.tiles";
    }
}
