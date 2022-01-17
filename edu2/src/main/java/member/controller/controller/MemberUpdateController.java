package member.controller.controller;

import member.controller.Controller;
import member.domain.member.Member;
import member.domain.member.MemberDao;

import java.util.Map;

public class MemberUpdateController implements Controller {
    MemberDao memberDao = new MemberDao();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String id = paramMap.get("id");
        String password = paramMap.get("password");
        String name = paramMap.get("name");
        String email = paramMap.get("email");

        Member member = new Member(id, password, name, email);
        memberDao.update(member);

        model.put("member", member);

        return "memberList/" + member.getId();
    }
}
