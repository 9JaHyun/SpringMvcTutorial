package controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController implements Controller {
    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        String welCome = "반갑습니다";
        model.put("msg", welCome);
        return "member/memberList.tiles";
    }
}
