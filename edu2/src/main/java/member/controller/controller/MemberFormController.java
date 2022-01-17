package member.controller.controller;

import member.controller.Controller;

import java.util.Map;

public class MemberFormController implements Controller {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
