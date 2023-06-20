package com.example.demo.web.ba04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * item削除画面コントローラ
 */
@Controller
public class BA0401Controller {

    /**
     * item削除画面表示する。
     * @param model Model
     * @return item削除画面
     */
    @GetMapping("/WBA0401/index")
    public String index(Model model) {
        return "BA0401/delete";
    }
}
