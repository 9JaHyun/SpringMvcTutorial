package controller;

import java.util.Map;

public interface Controller {
    String execute(Map<String, String> paramMap, Map<String, Object> model);
}
