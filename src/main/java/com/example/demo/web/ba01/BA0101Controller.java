package com.example.demo.web.ba01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.core.exception.AppException;

/*
 * item登録画面コントローラ
 */
@Controller
public class BA0101Controller {

    @Autowired
    private ItemRegistService itemRegistService;

    /** ロガー */
    Logger logger = LoggerFactory.getLogger(BA0101Controller.class);

    /**
     * 表示
     * @param model Model
     * @return 登録画面
     */
    @GetMapping("/WBA0101/index")
    public String index(Model model) {
        logger.info("登録画面表示");
        model.addAttribute("itemForm", new ItemForm(1, "1", 1));
        return "BA0101/regist";
    }

    /**
     * 確認
     * @param itemForm ItemForm
     * @param result BindingResult
     * @return 確認画面 or 登録画面
     */
    @PostMapping("/WBA0101/confirm")
    public String confirm(@Validated ItemForm itemForm, BindingResult result) {
        if (result.hasErrors()) {
            return "/BA0101/regist";
        }
        return "BA0101/confirm";
    }

    /**
     * 登録
     * @param itemForm ItemForm
     * @param result BindingResult
     * @return　完了画面 or 登録画面
     */
    @PostMapping(path="/WBA0101/regist", params="regist")
    public String regist(@Validated ItemForm itemForm, BindingResult result) {

        if (result.hasErrors()) {
            return "/BA0101/regist";
        }

        try {
            itemRegistService.registItem(itemForm.toItem());
        } catch(AppException e) {
            if (e.getField() != null) {
                result.rejectValue(e.getField(), e.getMessageId());
            } else {
                result.reject(e.getMessageId());
            }
            return "BA0101/regist";
        }
        
        return "redirect:/WBA0101/complete";
    }
    
    /**
     * 戻る
     * @param itemForm ItemForm
     * @return 登録画面
     */
    @PostMapping(path="/WBA0101/regist", params="back")
    public String back(ItemForm itemForm) {
        return "BA0101/regist";
    }

    /**
     * 完了
     * @return 完了画面
     */
    @GetMapping("/WBA0101/complete")
    public String complete() {
        return "BA0101/complete";
    }
}
