package com.example.demo.web.aa01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TOP画面コントローラ
 */
@Controller
public class AA0101Controller {

    /**
     * TOP画面表示する。
     * @return TOP画面
     */
    @GetMapping("/")
    public String top() {
        return "AA0101/index";
    }
}
