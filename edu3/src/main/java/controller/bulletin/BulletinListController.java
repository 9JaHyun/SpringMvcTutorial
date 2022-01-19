package controller.bulletin;

import controller.Controller;
import java.util.List;
import java.util.Map;
import dao.DAO;
import dao.BulletinDAO;
import service.BulletinService;
import vo.BulletinVO;

public class BulletinListController implements Controller {
    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        BulletinService bulletinService = new BulletinService();
        List<BulletinVO> bulletinVOList = bulletinService.findAll();
        model.put("bulletinList", bulletinVOList);
        return "bulletin/bulletinList.tiles";
    }
}
