package controller.bulletin;

import controller.Controller;
import java.util.Map;
import service.BulletinService;
import vo.BulletinVO;

public class BulletinUpdateController implements Controller {
    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        BulletinService bulletinService = new BulletinService();
        String id = paramMap.get("id");
        String title = paramMap.get("title");
        String content = paramMap.get("content");

        BulletinVO vo = new BulletinVO();
        vo.setBbsId(Integer.parseInt(id));
        vo.setBbsTitle(title);
        vo.setBbsContent(content);

        BulletinVO update = bulletinService.update(vo);
        return "bulletin/bulletinList.do";
    }
}
