package controller;

import java.util.Map;

public class BulletinSaveController implements Controller{

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        return "bulletin/bulletinSave.tiles";
    }
}
