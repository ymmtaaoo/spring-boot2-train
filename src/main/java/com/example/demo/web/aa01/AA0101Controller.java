package com.example.demo.web.aa01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * トップ画面コントローラ
 */
@Controller
public class AA0101Controller {

    /**
     * トップ画面表示する。itemを全件表示する
     * @param model Model
     * @return top画面
     */
    @GetMapping("/")
    public String top() {
        return "AA0101/index";
    }
}
