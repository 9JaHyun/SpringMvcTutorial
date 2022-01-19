package controller.bulletin;

import controller.Controller;
import java.util.Map;
import dao.BulletinDAO;
import service.BulletinService;
import vo.BulletinVO;

public class BulletinSelectController implements Controller {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        BulletinService service = new BulletinService();

        int id = Integer.parseInt(paramMap.get("id"));
        BulletinVO bulletinVO = service.findById(id);
        model.put("bulletin", bulletinVO);
        return "bulletin/bulletinInfo.tiles";
    }
}
