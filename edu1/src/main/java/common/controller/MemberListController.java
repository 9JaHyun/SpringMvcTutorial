package common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class MemberListController implements Controller {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getParameterNames().asIterator().forEachRemaining(System.out::println);
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String score = request.getParameter("score");

        System.out.println(id + " - " + name + " - " + score);

        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        Member member = new Member(1, "Hong");
        Member member1 = new Member(2, "Hong1");
        Member member2 = new Member(3, "Hong2");

        List<Member> memberList = new ArrayList<>();
        memberList.add(member);
        memberList.add(member1);
        memberList.add(member2);

        response.getWriter().print(objectMapper.writeValueAsString(memberList));
    }
}
