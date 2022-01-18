package member.controller.controller;

import member.controller.Controller;
import member.domain.Member;
import member.domain.MemberDao;

import java.util.Map;

public class MemberSaveController implements Controller {
    private MemberDao memberDao = new MemberDao();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String id = paramMap.get("id");
        String password = paramMap.get("password");
        String name = paramMap.get("name");
        String email = paramMap.get("email");

        Member member = new Member(id, password, name, email);
        memberDao.save(member);

        model.put("member", member);

        return "../index";
    }
}
