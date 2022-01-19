package controller.bulletin;

import controller.Controller;
import java.util.Map;
import service.BulletinService;
import vo.BulletinVO;

public class BulletinDeleteController implements Controller {
    BulletinService bulletinService = new BulletinService();

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        String id = paramMap.get("id");
        bulletinService.delete(Integer.parseInt(id));
        return "bulletin/bulletinList.tiles";
    }
}
