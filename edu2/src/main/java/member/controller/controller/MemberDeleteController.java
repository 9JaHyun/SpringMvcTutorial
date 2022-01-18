package member.controller.controller;

import member.controller.Controller;
import member.domain.MemberDao;

import java.util.Map;

public class MemberDeleteController implements Controller {
    private MemberDao memberDao = new MemberDao();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String id = paramMap.get("id");
        memberDao.deleteById(id);

        return "commentsList";
    }
}
