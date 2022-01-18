package controller;

import java.util.Map;
import service.BulletinService;
import serviceImpl.BulletinDAO;

public class BulletinFormController implements Controller{

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        return "bulletin/bulletinForm.tiles";
    }
}
