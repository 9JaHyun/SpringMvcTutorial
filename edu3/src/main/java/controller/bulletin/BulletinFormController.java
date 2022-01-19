package controller.bulletin;

import controller.Controller;
import java.util.Map;

public class BulletinFormController implements Controller {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        return "bulletin/bulletinForm.tiles";
    }
}
