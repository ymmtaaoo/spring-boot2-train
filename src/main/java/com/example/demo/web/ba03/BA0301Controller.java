package com.example.demo.web.ba03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.core.exception.AppException;
import com.example.demo.entity.Item;

import lombok.RequiredArgsConstructor;

/**
 * item詳細画面コントローラ
 */
@Controller
@RequiredArgsConstructor
public class BA0301Controller {

    /** 詳細画面サービス */
    private final ItemDetailService itemDetailService;

    /** ロガー */
    private static final Logger logger = LoggerFactory.getLogger(BA0301Controller.class);

    /**
     * item詳細画面表示する。
     * @param form form
     * @param bindingResult bindingResult
     * @param model Model
     * @return item詳細画面
     */
    @GetMapping("/WBA0301/index")
    public String index(ItemDetailForm form, BindingResult bindingResult, Model model) {
        logger.info("詳細画面表示");
        if (form.getId() == null) {
             throw new AppException("ME999");
        }

        Item item = itemDetailService.findItemById(form.getId());
        model.addAttribute("itemForm", item);
        return "BA0301/index";
    }

}
