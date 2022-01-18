package controller;

import java.util.Map;
import serviceImpl.BulletinDAO;
import vo.BulletinVO;

public class BulletinSelectController implements Controller {

    BulletinDAO dao = BulletinDAO.getInstance();

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        int id = Integer.parseInt(paramMap.get("id"));
        BulletinVO bulletinVO = dao.selectOne(id);
        model.put("bulletin", bulletinVO);
        return "bulletin/bulletinInfo.tiles";
    }
}
