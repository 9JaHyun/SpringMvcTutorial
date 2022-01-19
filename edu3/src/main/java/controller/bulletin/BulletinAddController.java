package controller.bulletin;

import controller.Controller;
import dao.BulletinDAO;
import java.util.Map;
import service.BulletinService;
import vo.BulletinVO;

public class BulletinAddController implements Controller {


    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        BulletinService bulletinService = new BulletinService();
        BulletinVO vo = new BulletinVO();

        vo.setBbsTitle(paramMap.get("title"));
        vo.setBbsContent(paramMap.get("content"));
        vo.setBbsWriter(paramMap.get("writer"));
        vo.setBbsImage(paramMap.get("image"));

        bulletinService.save(vo);

        return "bulletin/bulletinList.tiles";
    }
}
