package controller;

import java.util.List;
import java.util.Map;
import service.BulletinService;
import serviceImpl.BulletinDAO;
import vo.BulletinVO;

public class BulletinListController implements Controller{
    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        BulletinService service = BulletinDAO.getInstance();
        List<BulletinVO> bulletinVOList = service.selectList();
        model.put("bulletinList", bulletinVOList);
        return "bulletin/bulletinList.tiles";
    }
}
