package com.example.demo.web.ba03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * item詳細画面コントローラ
 */
@Controller
public class BA0301Controller {

    /**
     * item詳細画面表示する。
     * @param model Model
     * @return item詳細画面
     */
    @GetMapping("/WBA0301/index")
    public String index() {
        return "BA0301/index";
    }
}
