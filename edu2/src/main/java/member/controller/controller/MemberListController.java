package member.controller.controller;

import comments.domain.comment.Comments;
import comments.domain.comment.CommentsDao;
import member.controller.Controller;
import member.domain.member.Member;
import member.domain.member.MemberDao;

import java.util.List;
import java.util.Map;

public class MemberListController implements Controller {
    private MemberDao memberDao = new MemberDao();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> memberList = memberDao.findAll();

        model.put("memberList", memberList);
        return "memberList";
    }
}
