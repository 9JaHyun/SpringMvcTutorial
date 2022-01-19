package controller;

import java.util.Map;

public class NoticeFormController implements Controller {

    @Override
    public String execute(Map<String, String> paramMap, Map<String, Object> model) {
        return "notice/noticeForm.tiles";
    }
}
