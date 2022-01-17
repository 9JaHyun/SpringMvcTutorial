package member.controller.SessionTest.controller;

import member.controller.SessionTest.SessionController;
import member.domain.member.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MemberLoginController implements SessionController {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member member = new Member("Session1", "세션", "password1", "email1");
        session.setAttribute("Login", member);
        request.getRequestDispatcher("sessionMain").forward(request, response);
    }
}
